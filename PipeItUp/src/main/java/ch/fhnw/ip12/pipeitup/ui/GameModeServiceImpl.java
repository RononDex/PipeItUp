package ch.fhnw.ip12.pipeitup.ui;

import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.inject.Inject;

import ch.fhnw.ip12.pipeitup.app.ExcludeMethodFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.logic.GraphLayoutLoader;
import ch.fhnw.ip12.pipeitup.logic.KruskalAlgorithm;
import ch.fhnw.ip12.pipeitup.logic.MinimumSpanningTreeService;
import ch.fhnw.ip12.pipeitup.logic.PrimAlgorithm;
import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;
import ch.fhnw.ip12.pipeitup.logic.Models.VertexModel;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeState;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoardState;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameMode;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GraphViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.VertexViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchViewModel;

class GameModeServiceImpl implements GameModeService {

	private static final int PENALTY_FOR_WRONG_EDGE_SECONDS = 5;

	private GraphLayoutLoader graphLayoutLoader;
	private KruskalAlgorithm kruskalAlgorithm;
	private PrimAlgorithm primAlgorithm;
	private MinimumSpanningTreeService minimumSpanningTreeService;

	@Inject
	public GameModeServiceImpl(GraphLayoutLoader graphLayoutLoader,
			MinimumSpanningTreeService minimumSpanningTreeService, PrimAlgorithm primAlgorithm,
			KruskalAlgorithm kruskalAlgorithm) {
		this.graphLayoutLoader = graphLayoutLoader;
		this.minimumSpanningTreeService = minimumSpanningTreeService;
		this.primAlgorithm = primAlgorithm;
		this.kruskalAlgorithm = kruskalAlgorithm;
	}

	@Override
	public void loadRandomWeightedGraph(PipeItUpGameViewModel viewModel) {

		GraphLayoutModel randomGraph = graphLayoutLoader.getRandomlyWeightedGraph(6);

		nodeCounter = 0;

		Set<VertexViewModel> vertexViewModels = randomGraph.getVertices().stream().map(vertex -> Map(vertex))
				.collect(Collectors.toSet());
		Set<EdgeViewModel> edgeViewModels = randomGraph.getEdges().stream().map(e -> Map(e, vertexViewModels))
				.collect(Collectors.toSet());

		viewModel.gameBoardViewModel.graphViewModel.setValue(new GraphViewModel(vertexViewModels, edgeViewModels));
		viewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.GAME_NOT_STARTED);
	}

	@Override
	public PipeItUpGameViewModel createStartUpViewModel() {
		PipeItUpGameViewModel viewModel = new PipeItUpGameViewModel();
		viewModel.touchViewModel = new TouchViewModel();

		loadRandomWeightedGraph(viewModel);

		viewModel.gameBoardViewModel.selectedEdgeForValidation.addListener(listener -> validateSelectedEdge(viewModel));
		viewModel.gameBoardViewModel.startNodeForPrim.addListener(listener -> setStartNodeForPrim(viewModel));
		viewModel.gameBoardViewModel.gameBoardState.addListener(
				(observable, oldValue, newValue) -> restartHighscoreTimerIfNeeded(viewModel, oldValue, newValue));
		viewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.GAME_NOT_STARTED);

		return viewModel;
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

			viewModel.gameBoardViewModel.selectedEdgeForValidation.setValue(null);
		}
	}

	private void validateEdgeWithAlgo(PipeItUpGameViewModel viewModel, EdgeViewModel selectedEdgeViewModel,
			GraphLayoutModel mappedLogicModel, EdgeModel selectedEdge) {
		if (viewModel.gameBoardViewModel.gameMode.getValue() == GameMode.KRUKSAL) {
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
		}

	}

	private static int nodeCounter = 0;

	@ExcludeMethodFromJacocoGeneratedReport
	private static VertexViewModel Map(VertexModel vertex) {
		nodeCounter++;
		return new VertexViewModel(nodeCounter, vertex.getPositionX(), vertex.getPositionY());
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
				edge.getWeight());
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
