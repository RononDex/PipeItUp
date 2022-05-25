package ch.fhnw.ip12.pipeitup.ui;

import ch.fhnw.ip12.pipeitup.app.ExcludeMethodFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.logic.GraphLayoutLoader;
import ch.fhnw.ip12.pipeitup.logic.HighscoreService;
import ch.fhnw.ip12.pipeitup.logic.KruskalAlgorithm;
import ch.fhnw.ip12.pipeitup.logic.MinimumSpanningTreeService;
import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;
import ch.fhnw.ip12.pipeitup.logic.Models.HighscoreEntryModel;
import ch.fhnw.ip12.pipeitup.logic.Models.VertexModel;
import ch.fhnw.ip12.pipeitup.logic.PrimAlgorithm;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeState;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoardState;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameMode;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GraphViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.MapMode;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.VertexViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.touch.HighscoreEntryViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchViewModel;
import com.google.inject.Inject;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.time.LocalTime;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

class GameModeServiceImpl implements GameModeService {

	private static final int PENALTY_FOR_WRONG_EDGE_SECONDS = 5;

	private GraphLayoutLoader graphLayoutLoader;
	private KruskalAlgorithm kruskalAlgorithm;
	private PrimAlgorithm primAlgorithm;
	private MinimumSpanningTreeService minimumSpanningTreeService;
	private HighscoreService highscoreService;

	@Inject
	public GameModeServiceImpl(GraphLayoutLoader graphLayoutLoader,
							   MinimumSpanningTreeService minimumSpanningTreeService, PrimAlgorithm primAlgorithm,
							   KruskalAlgorithm kruskalAlgorithm, HighscoreService highscoreService) {
		this.graphLayoutLoader = graphLayoutLoader;
		this.minimumSpanningTreeService = minimumSpanningTreeService;
		this.primAlgorithm = primAlgorithm;
		this.kruskalAlgorithm = kruskalAlgorithm;
		this.highscoreService = highscoreService;
	}

	@Override
	public void loadRandomWeightedGraph(PipeItUpGameViewModel viewModel) {

		GraphLayoutModel randomGraph = graphLayoutLoader.getRandomlyWeightedGraph(6);

		nodeCounter = 0;

		Set<VertexViewModel> vertexViewModels = randomGraph.getVertices().stream().map(vertex -> Map(vertex))
			.collect(Collectors.toSet());
		Set<EdgeViewModel> edgeViewModels = randomGraph.getEdges().stream().map(e -> Map(e, vertexViewModels))
				.collect(Collectors.toSet());
		edgeViewModels.forEach(e -> e.edgeState.setValue(EdgeState.DEACTIVATED));

		viewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.GAME_NOT_STARTED);
		viewModel.gameBoardViewModel.graphViewModel.setValue(new GraphViewModel(vertexViewModels, edgeViewModels));
		viewModel.gameBoardViewModel.graphViewModel.getValue().vertexViewModels
				.forEach(v -> v.isLedActive.setValue(false));
	}

	@Override
	public PipeItUpGameViewModel createStartUpViewModel() {
		PipeItUpGameViewModel viewModel = new PipeItUpGameViewModel();
		viewModel.touchViewModel = new TouchViewModel();

		loadRandomWeightedGraph(viewModel);

		viewModel.touchViewModel.highscoreEntries = new SimpleListProperty<>(
				FXCollections.observableArrayList(highscoreService
						.getHighscoreEntries().stream().map(hs -> new HighscoreEntryViewModel(hs.getUserName(),
								hs.getScoreInSeconds(), GameMode.valueOf(hs.getGameMode()).get()))
						.collect(Collectors.toList())));
		viewModel.gameBoardViewModel.selectedEdgeForValidation.addListener(listener -> validateSelectedEdge(viewModel));
		viewModel.gameBoardViewModel.startNodeForPrim.addListener(listener -> setStartNodeForPrim(viewModel));
		viewModel.gameBoardViewModel.gameBoardState.addListener(
			(observable, oldValue, newValue) -> restartHighscoreTimerIfNeeded(viewModel, oldValue, newValue));
		viewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.GAME_NOT_STARTED);
		viewModel.gameBoardViewModel.resetGameBoard.addListener(listener -> resetGameBoard(viewModel));
		viewModel.gameBoardViewModel.startNewGame.addListener(listener -> startNewGame(viewModel));
		viewModel.gameBoardViewModel.showSolution.addListener(listener -> showSolution(viewModel));
		viewModel.touchViewModel.newHighscoreEntryToSave.addListener(listener -> saveNewHighscoreEntry(viewModel));
		viewModel.touchViewModel.deleteAllHighscoreEntries
				.addListener(listener -> deleteAllHighscoreEntries(viewModel));

		viewModel.gameBoardViewModel.resetGameBoard.setValue(true);

		return viewModel;
	}

	@Override
	public void deleteAllHighscoreEntries(PipeItUpGameViewModel viewModel) {
		if (viewModel.touchViewModel.deleteAllHighscoreEntries.getValue()) {

			highscoreService.clearHighscoreEntries();
			viewModel.touchViewModel.highscoreEntries.clear();

			viewModel.touchViewModel.deleteAllHighscoreEntries.setValue(false);
		}
	}

	@Override
	public void saveNewHighscoreEntry(PipeItUpGameViewModel viewModel) {
		if (viewModel.touchViewModel.newHighscoreEntryToSave.getValue() != null) {
			HighscoreEntryViewModel newEntry = viewModel.touchViewModel.newHighscoreEntryToSave.getValue();
			highscoreService.saveHighscoreEntry(new HighscoreEntryModel(newEntry.userName.get(),
					newEntry.highscoreInSeconds.get(), newEntry.gameMode.getValue().value));
			viewModel.touchViewModel.newHighscoreEntryToSave.setValue(null);
			viewModel.touchViewModel.highscoreEntries.add(newEntry);
		}
	}

	@Override
	public void validateSelectedEdge(PipeItUpGameViewModel viewModel) {
		if (viewModel.gameBoardViewModel.selectedEdgeForValidation.getValue() != null) {

			EdgeViewModel selectedEdgeViewModel = viewModel.gameBoardViewModel.selectedEdgeForValidation.getValue();
			GraphLayoutModel mappedLogicModel = Map(viewModel.gameBoardViewModel.graphViewModel.getValue());
			EdgeModel selectedEdge = mappedLogicModel.getEdges().stream().filter(
					e -> e.getVertex1().getPositionX() == selectedEdgeViewModel.vertex1.getVertexCenterPositionXInMm()
						&& e.getVertex1().getPositionY() == selectedEdgeViewModel.vertex1
						.getVertexCenterPositionYInMm()
						&& e.getVertex2().getPositionX() == selectedEdgeViewModel.vertex2
						.getVertexCenterPositionXInMm()
						&& e.getVertex2().getPositionY() == selectedEdgeViewModel.vertex2
						.getVertexCenterPositionYInMm())
				.findFirst().get();
			VertexViewModel selectedStartVertexForPrimViewModel = viewModel.gameBoardViewModel.startNodeForPrim
				.getValue();
			VertexModel startNodeForPrim = selectedStartVertexForPrimViewModel != null ? mappedLogicModel.getVertices()
				.stream()
				.filter(v -> v.getPositionX() == selectedStartVertexForPrimViewModel.getVertexCenterPositionXInMm()
					&& v.getPositionY() == selectedStartVertexForPrimViewModel.getVertexCenterPositionYInMm())
				.findFirst().get() : null;
			mappedLogicModel.setStartVertexForPrim(startNodeForPrim);

			resetInvalidSelections(viewModel);

			if (viewModel.gameBoardViewModel.gameBoardState.getValue() == GameBoardState.SELECT_NEXT_EDGE
				&& viewModel.gameBoardViewModel.selectedEdgeForValidation.getValue().edgeState
				.get() == EdgeState.UNSELECTED) {

				validateEdgeWithAlgo(viewModel, selectedEdgeViewModel, mappedLogicModel, selectedEdge);

			}

			if (viewModel.gameBoardViewModel.selectedEdgeForValidation.getValue().edgeState
					.getValue() == EdgeState.SELECTED) {
				viewModel.gameBoardViewModel.selectedEdgeForValidation.getValue().vertex1.isLedActive.setValue(true);
				viewModel.gameBoardViewModel.selectedEdgeForValidation.getValue().vertex2.isLedActive.setValue(true);
			}

			viewModel.gameBoardViewModel.selectedEdgeForValidation.setValue(null);
		}
	}

	private void validateEdgeWithAlgo(PipeItUpGameViewModel viewModel, EdgeViewModel selectedEdgeViewModel,
			GraphLayoutModel mappedLogicModel, EdgeModel selectedEdge) {
		if (viewModel.gameBoardViewModel.gameMode.getValue() == GameMode.KRUSKAL) {
			boolean isValidPick = kruskalAlgorithm.isEdgeValidPick(mappedLogicModel, selectedEdge);
			if (isValidPick) {
				selectedEdgeViewModel.edgeState.setValue(EdgeState.SELECTED);
				selectedEdge.setUsed(true);
			} else
				selectedEdgeViewModel.edgeState.setValue(EdgeState.INVALID_SELECTION);
		} else { // Prim
			boolean isValidPick = primAlgorithm.isEdgeValidPick(mappedLogicModel, selectedEdge);
			if (isValidPick) {
				selectedEdgeViewModel.edgeState.setValue(EdgeState.SELECTED);
				selectedEdge.setUsed(true);
			} else
				selectedEdgeViewModel.edgeState.setValue(EdgeState.INVALID_SELECTION);
		}

		if (minimumSpanningTreeService.isMspCompleted(mappedLogicModel)) {
			viewModel.gameBoardViewModel.gameEndMomentForHighscore.setValue(LocalTime.now());
			viewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.GAME_FINISHED);
		}

		if (selectedEdgeViewModel.edgeState.get() == EdgeState.INVALID_SELECTION) {
			addPenaltyForWrongEdgePickToHighscore(viewModel);
		}
	}

	private void resetInvalidSelections(PipeItUpGameViewModel viewModel) {
		viewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels.stream()
			.filter(e -> e.edgeState.get() == EdgeState.INVALID_SELECTION)
			.forEach(e -> e.edgeState.setValue(EdgeState.UNSELECTED));
	}

	private void restartHighscoreTimerIfNeeded(PipeItUpGameViewModel viewModel, GameBoardState oldValue,
											   GameBoardState newValue) {
		if ((oldValue == GameBoardState.GAME_NOT_STARTED || oldValue == GameBoardState.GAME_FINISHED)
			&& (newValue == GameBoardState.SELECT_NEXT_EDGE || newValue == GameBoardState.SELECT_START_NODE)) {
			viewModel.gameBoardViewModel.gameStartMomentForHighscore.setValue(LocalTime.now());
		}
	}

	private void addPenaltyForWrongEdgePickToHighscore(PipeItUpGameViewModel viewModel) {
		viewModel.gameBoardViewModel.gameStartMomentForHighscore
			.setValue(viewModel.gameBoardViewModel.gameStartMomentForHighscore.getValue()
				.minusSeconds(PENALTY_FOR_WRONG_EDGE_SECONDS));
	}

	@Override
	public void setStartNodeForPrim(PipeItUpGameViewModel viewModel) {
		if (viewModel.gameBoardViewModel.startNodeForPrim.getValue() != null
			&& viewModel.gameBoardViewModel.gameBoardState.getValue() == GameBoardState.SELECT_START_NODE) {
			viewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.SELECT_NEXT_EDGE);
			viewModel.gameBoardViewModel.startNodeForPrim.getValue().isLedActive.setValue(true);
		}

	}

	@Override
	public void resetGameBoard(PipeItUpGameViewModel viewModel) {
		if (viewModel.gameBoardViewModel.resetGameBoard.getValue()) {
			loadRandomWeightedGraph(viewModel);
			viewModel.gameBoardViewModel.graphViewModel.getValue().vertexViewModels
					.forEach(v -> v.isLedActive.setValue(false));

			viewModel.gameBoardViewModel.startNodeForPrim.setValue(null);
			viewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.GAME_NOT_STARTED);
			viewModel.gameBoardViewModel.resetGameBoard.setValue(false);
		}
	}

	@Override
	public void startNewGame(PipeItUpGameViewModel viewModel) {
		if (viewModel.gameBoardViewModel.startNewGame.getValue()) {
			if (viewModel.gameBoardViewModel.gameBoardState.getValue() == GameBoardState.GAME_NOT_STARTED) {
				if (viewModel.gameBoardViewModel.selectedMapMode.getValue() == MapMode.RANDOM_WEIGHTS) {
					loadRandomWeightedGraph(viewModel);
					viewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels
							.forEach(e -> e.edgeState.setValue(EdgeState.UNSELECTED));
				}

				if (viewModel.gameBoardViewModel.gameMode.getValue() == GameMode.KRUSKAL) {
					viewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.SELECT_NEXT_EDGE);
				} else if (viewModel.gameBoardViewModel.gameMode.getValue() == GameMode.PRIM) {
					viewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.SELECT_START_NODE);

					Random rand = new Random();
					int randomVertexIndex = rand
							.nextInt(viewModel.gameBoardViewModel.graphViewModel.getValue().vertexViewModels.size());
					viewModel.gameBoardViewModel.startNodeForPrim.setValue(
							(VertexViewModel) viewModel.gameBoardViewModel.graphViewModel.getValue().vertexViewModels
									.toArray()[randomVertexIndex]);
				}
			}
			viewModel.gameBoardViewModel.startNewGame.setValue(false);
		}
	}

	@Override
	public void showSolution(PipeItUpGameViewModel viewModel) {
		if (viewModel.gameBoardViewModel.showSolution.getValue()) {
			GraphLayoutModel mappedLogicModel = Map(viewModel.gameBoardViewModel.graphViewModel.getValue());

			while (!minimumSpanningTreeService.isMspCompleted(mappedLogicModel)) {
				for (EdgeModel edge : mappedLogicModel.getEdges().stream().filter(e -> !e.isUsed())
						.collect(Collectors.toList())) {
					if (kruskalAlgorithm.isEdgeValidPick(mappedLogicModel, edge)) {
						edge.setUsed(true);
					}
				}
			}

			for (EdgeModel edge : mappedLogicModel.getEdges().stream().filter(e -> e.isUsed())
					.collect(Collectors.toList())) {
				viewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels.stream()
						.filter(e -> e.vertex1.getVertexCenterPositionXInMm() == edge.getVertex1().getPositionX()
								&& e.vertex1.getVertexCenterPositionYInMm() == edge.getVertex1().getPositionY()
								&& edge.getVertex2().getPositionX() == e.vertex2.getVertexCenterPositionXInMm()
								&& edge.getVertex2().getPositionY() == e.vertex2.getVertexCenterPositionYInMm())
						.findFirst().get().edgeState.setValue(EdgeState.SELECTED);
			}

			viewModel.gameBoardViewModel.showSolution.setValue(false);
		}
	}

	private static int nodeCounter = 0;

	@ExcludeMethodFromJacocoGeneratedReport
	private static VertexViewModel Map(VertexModel vertex) {
		nodeCounter++;
		return new VertexViewModel(nodeCounter, vertex.getPositionX(), vertex.getPositionY(), vertex.getLED(),
				vertex.getLEDLine());
	}

	@ExcludeMethodFromJacocoGeneratedReport
	private static VertexModel Map(VertexViewModel vertex) {
		return new VertexModel(vertex.getVertexCenterPositionXInMm(), vertex.getVertexCenterPositionYInMm());
	}

	@ExcludeMethodFromJacocoGeneratedReport
	private static EdgeViewModel Map(EdgeModel edge, Set<VertexViewModel> vertexList) {
		return new EdgeViewModel(
				vertexList.stream()
						.filter(v -> v.getVertexCenterPositionXInMm() == edge.getVertex1().getPositionX()
								&& v.getVertexCenterPositionYInMm() == edge.getVertex1().getPositionY())
						.findFirst().get(),
				vertexList.stream()
						.filter(v -> v.getVertexCenterPositionXInMm() == edge.getVertex2().getPositionX()
								&& v.getVertexCenterPositionYInMm() == edge.getVertex2().getPositionY())
						.findFirst().get(),
				edge.getWeight(), edge.getHardwareInfo());
	}

	@ExcludeMethodFromJacocoGeneratedReport
	private static EdgeModel Map(EdgeViewModel edge, Set<VertexModel> vertexList) {
		EdgeModel edgeModel = new EdgeModel(
			vertexList.stream()
				.filter(v -> v.getPositionX() == edge.vertex1.getVertexCenterPositionXInMm()
					&& v.getPositionY() == edge.vertex1.getVertexCenterPositionYInMm())
				.findFirst().get(),
			vertexList.stream()
				.filter(v -> v.getPositionX() == edge.vertex2.getVertexCenterPositionXInMm()
					&& v.getPositionY() == edge.vertex2.getVertexCenterPositionYInMm())
				.findFirst().get(),
			edge.weight.get());

		edgeModel.setUsed(edge.edgeState.get() == EdgeState.SELECTED);
		return edgeModel;
	}

	@ExcludeMethodFromJacocoGeneratedReport
	private static GraphLayoutModel Map(GraphViewModel graphLayout) {
		Set<VertexModel> mappedVertices = graphLayout.vertexViewModels.stream().map(v -> Map(v))
			.collect(Collectors.toSet());
		Set<EdgeModel> mappedEdges = graphLayout.edgeViewModels.stream().map(e -> Map(e, mappedVertices))
			.collect(Collectors.toSet());

		return new GraphLayoutModel(mappedVertices, mappedEdges);
	}
}
