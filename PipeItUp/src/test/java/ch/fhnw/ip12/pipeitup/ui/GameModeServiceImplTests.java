package ch.fhnw.ip12.pipeitup.ui;

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
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.MapMode;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.VertexViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.touch.HighscoreEntryViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * ViewModelServiceImplTests
 */
public class GameModeServiceImplTests {
	@Test
	void createStartUpViewModel_WithNoParameters_ReturnsNewViewModelInStartUpState() {
		// Arrange
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		PrimAlgorithm primAlgorithmMock = mock(PrimAlgorithm.class);
		KruskalAlgorithm kruskalAlgorithmMock = mock(KruskalAlgorithm.class);
		HighscoreService highscoreServiceMock = mock(HighscoreService.class);
		GameModeServiceImpl testee = new GameModeServiceImpl(graphLayoutLoaderMock, minimumSpanningTreeServiceMock,
				primAlgorithmMock, kruskalAlgorithmMock, highscoreServiceMock);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		when(graphLayoutLoaderMock.getRandomlyWeightedGraph(anyInt()))
				.thenReturn(new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges)));

		// Act
		PipeItUpGameViewModel actual = testee.createStartUpViewModel();
		actual.gameBoardViewModel.selectedEdgeForValidation.setValue(null);
		actual.gameBoardViewModel.startNodeForPrim.setValue(null);

		// Assert
		assertNotNull(actual);
		assertNotNull(actual.touchViewModel);
		assertNotNull(actual.gameBoardViewModel);
		assertEquals(2, actual.gameBoardViewModel.graphViewModel.getValue().vertexViewModels.size());
		assertEquals(1, actual.gameBoardViewModel.graphViewModel.getValue().edgeViewModels.size());
		assertEquals(5, actual.gameBoardViewModel.graphViewModel.getValue().edgeViewModels.stream()
				.collect(Collectors.toList()).get(0).weight.get());
		assertEquals(TouchScene.START_SCREEN, actual.touchViewModel.currentScene.get());
		assertEquals(null, actual.gameBoardViewModel.gameMode.getValue());
		assertEquals(GameBoardState.GAME_NOT_STARTED, actual.gameBoardViewModel.gameBoardState.getValue());
	}

	@Test
	void loadRandomWeightedGraph_WithEmptyViewModel_ReturnsNewGraphModelWithRandomWeights() {
		// Arrange
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		PrimAlgorithm primAlgorithmMock = mock(PrimAlgorithm.class);
		KruskalAlgorithm kruskalAlgorithmMock = mock(KruskalAlgorithm.class);
		HighscoreService highscoreServiceMock = mock(HighscoreService.class);
		GameModeServiceImpl testee = new GameModeServiceImpl(graphLayoutLoaderMock, minimumSpanningTreeServiceMock,
				primAlgorithmMock, kruskalAlgorithmMock, highscoreServiceMock);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		when(graphLayoutLoaderMock.getRandomlyWeightedGraph(anyInt()))
				.thenReturn(new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges)));
		PipeItUpGameViewModel existingViewModel = new PipeItUpGameViewModel();

		// Act
		testee.loadRandomWeightedGraph(existingViewModel);

		// Assert
		assertNotNull(existingViewModel);
		assertNotNull(existingViewModel.gameBoardViewModel);
		assertEquals(2, existingViewModel.gameBoardViewModel.graphViewModel.getValue().vertexViewModels.size());
		assertEquals(1, existingViewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels.size());
		assertEquals(5, existingViewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels.stream()
				.collect(Collectors.toList()).get(0).weight.get());
		assertEquals(null, existingViewModel.gameBoardViewModel.gameMode.getValue());
		assertEquals(GameBoardState.GAME_NOT_STARTED, existingViewModel.gameBoardViewModel.gameBoardState.getValue());
	}

	@Test
	void validateSelectedEdge_WithNothingSelected_DoesNothing() {
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		PrimAlgorithm primAlgorithmMock = mock(PrimAlgorithm.class);
		KruskalAlgorithm kruskalAlgorithmMock = mock(KruskalAlgorithm.class);
		HighscoreService highscoreServiceMock = mock(HighscoreService.class);
		GameModeServiceImpl testee = new GameModeServiceImpl(graphLayoutLoaderMock, minimumSpanningTreeServiceMock,
				primAlgorithmMock, kruskalAlgorithmMock, highscoreServiceMock);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		PipeItUpGameViewModel existingViewModel = new PipeItUpGameViewModel();

		testee.validateSelectedEdge(existingViewModel);

		verifyNoMoreInteractions(graphLayoutLoaderMock, minimumSpanningTreeServiceMock, primAlgorithmMock,
				kruskalAlgorithmMock);
	}

	@Test
	void validateSelectedEdge_WithEdgeSelectedButNotInSelectedNextEdgeState_DoesNothing() {
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		PrimAlgorithm primAlgorithmMock = mock(PrimAlgorithm.class);
		KruskalAlgorithm kruskalAlgorithmMock = mock(KruskalAlgorithm.class);
		HighscoreService highscoreServiceMock = mock(HighscoreService.class);
		GameModeServiceImpl testee = new GameModeServiceImpl(graphLayoutLoaderMock, minimumSpanningTreeServiceMock,
				primAlgorithmMock, kruskalAlgorithmMock, highscoreServiceMock);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		vertices.add(new VertexModel(120d, 120d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		edges.add(new EdgeModel(vertices.get(1), vertices.get(2), 4));
		when(graphLayoutLoaderMock.getRandomlyWeightedGraph(anyInt()))
				.thenReturn(new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges)));
		PipeItUpGameViewModel existingViewModel = new PipeItUpGameViewModel();
		testee.loadRandomWeightedGraph(existingViewModel);
		existingViewModel.gameBoardViewModel.gameMode.setValue(GameMode.KRUSKAL);
		existingViewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.GAME_NOT_STARTED);
		existingViewModel.gameBoardViewModel.selectedEdgeForValidation
				.setValue(existingViewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels.stream()
						.findFirst().get());

		testee.validateSelectedEdge(existingViewModel);

		verify(graphLayoutLoaderMock, times(1)).getRandomlyWeightedGraph(anyInt());
		verifyNoMoreInteractions(graphLayoutLoaderMock, minimumSpanningTreeServiceMock, primAlgorithmMock,
				kruskalAlgorithmMock);
	}

	@Test
	void validateSelectedEdge_WithKruskalModeAndEdgeSelectedAndEdgeValid_SetsEdgeToValidAndClearsSelectedEdgeAndResetsInvalidSelectedEdges() {
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		PrimAlgorithm primAlgorithmMock = mock(PrimAlgorithm.class);
		KruskalAlgorithm kruskalAlgorithmMock = mock(KruskalAlgorithm.class);
		HighscoreService highscoreServiceMock = mock(HighscoreService.class);
		GameModeServiceImpl testee = new GameModeServiceImpl(graphLayoutLoaderMock, minimumSpanningTreeServiceMock,
				primAlgorithmMock, kruskalAlgorithmMock, highscoreServiceMock);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		vertices.add(new VertexModel(120d, 120d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		edges.add(new EdgeModel(vertices.get(1), vertices.get(2), 4));
		when(graphLayoutLoaderMock.getRandomlyWeightedGraph(anyInt()))
				.thenReturn(new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges)));
		when(kruskalAlgorithmMock.isEdgeValidPick(any(), any())).thenReturn(true);
		when(minimumSpanningTreeServiceMock.isMspCompleted(any())).thenReturn(false);
		PipeItUpGameViewModel existingViewModel = testee.createStartUpViewModel();
		testee.loadRandomWeightedGraph(existingViewModel);
		existingViewModel.gameBoardViewModel.gameMode.setValue(GameMode.KRUSKAL);
		existingViewModel.gameBoardViewModel.selectedMapMode.setValue(MapMode.RANDOM_WEIGHTS);
		existingViewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.GAME_NOT_STARTED);
		existingViewModel.gameBoardViewModel.startNewGame.setValue(true);
		EdgeViewModel secondEdge = existingViewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels
				.stream().skip(1).findFirst().get();
		secondEdge.edgeState.setValue(EdgeState.INVALID_SELECTION);
		existingViewModel.gameBoardViewModel.selectedEdgeForValidation
				.setValue(existingViewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels.stream()
						.findFirst().get());

		verify(kruskalAlgorithmMock, times(1)).isEdgeValidPick(any(), any());
		verify(graphLayoutLoaderMock, times(4)).getRandomlyWeightedGraph(anyInt());
		verify(minimumSpanningTreeServiceMock, times(1)).isMspCompleted(any());
		verifyNoMoreInteractions(graphLayoutLoaderMock, minimumSpanningTreeServiceMock, primAlgorithmMock,
				kruskalAlgorithmMock);
		assertNull(existingViewModel.gameBoardViewModel.selectedEdgeForValidation.getValue());
		assertEquals(EdgeState.SELECTED, existingViewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels
				.stream().findFirst().get().edgeState.getValue());
		assertEquals(EdgeState.UNSELECTED, secondEdge.edgeState.getValue());
	}

	@Test
	void validateSelectedEdge_WithKruskalModeAndEdgeSelectedAndEdgeInvalid_SetsEdgeToInvalidAndClearsSelectedEdgeAndAddsPenalty() {
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		PrimAlgorithm primAlgorithmMock = mock(PrimAlgorithm.class);
		KruskalAlgorithm kruskalAlgorithmMock = mock(KruskalAlgorithm.class);
		HighscoreService highscoreServiceMock = mock(HighscoreService.class);
		GameModeServiceImpl testee = new GameModeServiceImpl(graphLayoutLoaderMock, minimumSpanningTreeServiceMock,
				primAlgorithmMock, kruskalAlgorithmMock, highscoreServiceMock);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		when(graphLayoutLoaderMock.getRandomlyWeightedGraph(anyInt()))
				.thenReturn(new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges)));
		when(kruskalAlgorithmMock.isEdgeValidPick(any(), any())).thenReturn(false);
		when(minimumSpanningTreeServiceMock.isMspCompleted(any())).thenReturn(false);
		PipeItUpGameViewModel existingViewModel = testee.createStartUpViewModel();
		existingViewModel.gameBoardViewModel.gameMode.setValue(GameMode.KRUSKAL);
		existingViewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.GAME_NOT_STARTED);
		existingViewModel.gameBoardViewModel.selectedMapMode.setValue(MapMode.RANDOM_WEIGHTS);
		existingViewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels
				.forEach(e -> e.edgeState.setValue(EdgeState.UNSELECTED));
		existingViewModel.gameBoardViewModel.startNewGame.setValue(true);
		existingViewModel.gameBoardViewModel.selectedEdgeForValidation
				.setValue(existingViewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels.stream()
						.findFirst().get());
		LocalTime startDate = existingViewModel.gameBoardViewModel.gameStartMomentForHighscore.getValue();

		verify(kruskalAlgorithmMock, times(1)).isEdgeValidPick(any(), any());
		verify(graphLayoutLoaderMock, times(3)).getRandomlyWeightedGraph(anyInt());
		verify(minimumSpanningTreeServiceMock, times(1)).isMspCompleted(any());
		verifyNoMoreInteractions(graphLayoutLoaderMock, minimumSpanningTreeServiceMock, primAlgorithmMock,
				kruskalAlgorithmMock);
		assertNull(existingViewModel.gameBoardViewModel.selectedEdgeForValidation.getValue());
		assertEquals(EdgeState.INVALID_SELECTION,
				existingViewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels.stream().findFirst()
						.get().edgeState.getValue());
		assertEquals(5000, Math.abs(Duration.between(LocalTime.now(), startDate).toMillis()), 100);
	}

	@Test
	void validateSelectedEdge_WithPrimModeAndEdgeSelectedAndEdgeValid_SetsEdgeToValidAndClearsSelectedEdgeAndResetsInvalidSelectedEdges() {
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		PrimAlgorithm primAlgorithmMock = mock(PrimAlgorithm.class);
		KruskalAlgorithm kruskalAlgorithmMock = mock(KruskalAlgorithm.class);
		HighscoreService highscoreServiceMock = mock(HighscoreService.class);
		GameModeServiceImpl testee = new GameModeServiceImpl(graphLayoutLoaderMock, minimumSpanningTreeServiceMock,
				primAlgorithmMock, kruskalAlgorithmMock, highscoreServiceMock);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		vertices.add(new VertexModel(120d, 120d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		edges.add(new EdgeModel(vertices.get(1), vertices.get(2), 4));
		when(graphLayoutLoaderMock.getRandomlyWeightedGraph(anyInt()))
				.thenReturn(new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges)));
		when(primAlgorithmMock.isEdgeValidPick(any(), any())).thenReturn(true);
		when(minimumSpanningTreeServiceMock.isMspCompleted(any())).thenReturn(false);
		PipeItUpGameViewModel existingViewModel = testee.createStartUpViewModel();
		existingViewModel.gameBoardViewModel.gameMode.setValue(GameMode.PRIM);
		existingViewModel.gameBoardViewModel.selectedMapMode.setValue(MapMode.RANDOM_WEIGHTS);
		existingViewModel.gameBoardViewModel.startNewGame.setValue(true);
		existingViewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.SELECT_NEXT_EDGE);
		EdgeViewModel secondEdge = existingViewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels
				.stream().skip(1).findFirst().get();
		secondEdge.edgeState.setValue(EdgeState.INVALID_SELECTION);
		existingViewModel.gameBoardViewModel.startNodeForPrim.setValue(
				(VertexViewModel) existingViewModel.gameBoardViewModel.graphViewModel.getValue().vertexViewModels
						.toArray()[0]);
		existingViewModel.gameBoardViewModel.selectedEdgeForValidation
				.setValue(existingViewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels.stream()
						.findFirst().get());

		verify(primAlgorithmMock, times(1)).isEdgeValidPick(any(), any());
		verify(graphLayoutLoaderMock, times(3)).getRandomlyWeightedGraph(anyInt());
		verify(minimumSpanningTreeServiceMock, times(1)).isMspCompleted(any());
		verifyNoMoreInteractions(graphLayoutLoaderMock, minimumSpanningTreeServiceMock, primAlgorithmMock,
				kruskalAlgorithmMock);
		assertNull(existingViewModel.gameBoardViewModel.selectedEdgeForValidation.getValue());
		assertEquals(EdgeState.SELECTED, existingViewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels
				.stream().findFirst().get().edgeState.getValue());
		assertEquals(EdgeState.UNSELECTED, secondEdge.edgeState.getValue());
	}

	@Test
	void validateSelectedEdge_WithPrimModeAndEdgeSelectedAndEdgeInvalid_SetsEdgeToInvalidAndClearsSelectedEdge() {
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		PrimAlgorithm primAlgorithmMock = mock(PrimAlgorithm.class);
		KruskalAlgorithm kruskalAlgorithmMock = mock(KruskalAlgorithm.class);
		HighscoreService highscoreServiceMock = mock(HighscoreService.class);
		GameModeServiceImpl testee = new GameModeServiceImpl(graphLayoutLoaderMock, minimumSpanningTreeServiceMock,
				primAlgorithmMock, kruskalAlgorithmMock, highscoreServiceMock);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		when(graphLayoutLoaderMock.getRandomlyWeightedGraph(anyInt()))
				.thenReturn(new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges)));
		when(primAlgorithmMock.isEdgeValidPick(any(), any())).thenReturn(false);
		when(minimumSpanningTreeServiceMock.isMspCompleted(any())).thenReturn(false);
		PipeItUpGameViewModel existingViewModel = testee.createStartUpViewModel();
		existingViewModel.gameBoardViewModel.gameMode.setValue(GameMode.PRIM);
		existingViewModel.gameBoardViewModel.selectedMapMode.setValue(MapMode.RANDOM_WEIGHTS);
		existingViewModel.gameBoardViewModel.startNewGame.setValue(true);
		existingViewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.SELECT_NEXT_EDGE);
		existingViewModel.gameBoardViewModel.selectedMapMode.setValue(MapMode.RANDOM_WEIGHTS);
		existingViewModel.gameBoardViewModel.selectedEdgeForValidation
				.setValue(existingViewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels.stream()
						.findFirst().get());
		existingViewModel.gameBoardViewModel.startNodeForPrim.setValue(
				(VertexViewModel) existingViewModel.gameBoardViewModel.graphViewModel.getValue().vertexViewModels
						.toArray()[0]);

		testee.validateSelectedEdge(existingViewModel);

		verify(primAlgorithmMock, times(1)).isEdgeValidPick(any(), any());
		verify(graphLayoutLoaderMock, times(3)).getRandomlyWeightedGraph(anyInt());
		verify(minimumSpanningTreeServiceMock, times(1)).isMspCompleted(any());
		verifyNoMoreInteractions(graphLayoutLoaderMock, minimumSpanningTreeServiceMock, primAlgorithmMock,
				kruskalAlgorithmMock);
		assertNull(existingViewModel.gameBoardViewModel.selectedEdgeForValidation.getValue());
		assertEquals(EdgeState.INVALID_SELECTION,
				existingViewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels.stream().findFirst()
						.get().edgeState.getValue());
	}

	@Test
	void validateSelectedEdge_WithPrimModeAndEdgeSelectedAndEdgeValidAndMspCompleted_SetsEdgeToValidAndClearsSelectedEdgeAndResetsInvalidSelectedEdgesAndSetsGameStateToFinished() {
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		PrimAlgorithm primAlgorithmMock = mock(PrimAlgorithm.class);
		KruskalAlgorithm kruskalAlgorithmMock = mock(KruskalAlgorithm.class);
		HighscoreService highscoreServiceMock = mock(HighscoreService.class);
		GameModeServiceImpl testee = new GameModeServiceImpl(graphLayoutLoaderMock, minimumSpanningTreeServiceMock,
				primAlgorithmMock, kruskalAlgorithmMock, highscoreServiceMock);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		vertices.add(new VertexModel(120d, 120d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		edges.add(new EdgeModel(vertices.get(1), vertices.get(2), 4));
		edges.add(new EdgeModel(vertices.get(0), vertices.get(2), 4));
		when(graphLayoutLoaderMock.getRandomlyWeightedGraph(anyInt()))
				.thenReturn(new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges)));
		when(primAlgorithmMock.isEdgeValidPick(any(), any())).thenReturn(true);
		when(minimumSpanningTreeServiceMock.isMspCompleted(any())).thenReturn(true);
		PipeItUpGameViewModel existingViewModel = testee.createStartUpViewModel();
		existingViewModel.gameBoardViewModel.gameMode.setValue(GameMode.PRIM);
		existingViewModel.gameBoardViewModel.selectedMapMode.setValue(MapMode.RANDOM_WEIGHTS);
		existingViewModel.gameBoardViewModel.startNewGame.setValue(true);
		existingViewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.SELECT_NEXT_EDGE);
		existingViewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels
				.forEach(e -> e.edgeState.setValue(EdgeState.UNSELECTED));
		EdgeViewModel secondEdge = existingViewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels
				.stream().skip(1).findFirst().get();
		secondEdge.edgeState.setValue(EdgeState.INVALID_SELECTION);
		existingViewModel.gameBoardViewModel.startNodeForPrim.setValue(
				(VertexViewModel) existingViewModel.gameBoardViewModel.graphViewModel.getValue().vertexViewModels
						.toArray()[0]);
		existingViewModel.gameBoardViewModel.selectedEdgeForValidation
				.setValue(existingViewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels.stream()
						.findFirst().get());

		verify(primAlgorithmMock, times(1)).isEdgeValidPick(any(), any());
		verify(graphLayoutLoaderMock, times(3)).getRandomlyWeightedGraph(anyInt());
		verify(minimumSpanningTreeServiceMock, times(1)).isMspCompleted(any());
		verifyNoMoreInteractions(graphLayoutLoaderMock, minimumSpanningTreeServiceMock, primAlgorithmMock,
				kruskalAlgorithmMock);
		assertNull(existingViewModel.gameBoardViewModel.selectedEdgeForValidation.getValue());
		assertEquals(EdgeState.SELECTED, existingViewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels
				.stream().findFirst().get().edgeState.getValue());
		assertEquals(EdgeState.UNSELECTED, secondEdge.edgeState.getValue());
		assertEquals(GameBoardState.GAME_FINISHED, existingViewModel.gameBoardViewModel.gameBoardState.getValue());
	}

	@Test
	void validateSelectedEdge_WithAlreadyUsedEdge_DoesNothingAndSetsSelectedEdgeForValiationToNull() {
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		PrimAlgorithm primAlgorithmMock = mock(PrimAlgorithm.class);
		KruskalAlgorithm kruskalAlgorithmMock = mock(KruskalAlgorithm.class);
		HighscoreService highscoreServiceMock = mock(HighscoreService.class);
		GameModeServiceImpl testee = new GameModeServiceImpl(graphLayoutLoaderMock, minimumSpanningTreeServiceMock,
				primAlgorithmMock, kruskalAlgorithmMock, highscoreServiceMock);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		vertices.add(new VertexModel(120d, 120d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		edges.add(new EdgeModel(vertices.get(1), vertices.get(2), 4));
		edges.add(new EdgeModel(vertices.get(0), vertices.get(2), 4));
		when(graphLayoutLoaderMock.getRandomlyWeightedGraph(anyInt()))
				.thenReturn(new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges)));
		when(primAlgorithmMock.isEdgeValidPick(any(), any())).thenReturn(true);
		when(minimumSpanningTreeServiceMock.isMspCompleted(any())).thenReturn(true);
		PipeItUpGameViewModel existingViewModel = testee.createStartUpViewModel();
		existingViewModel.gameBoardViewModel.gameMode.setValue(GameMode.PRIM);
		existingViewModel.gameBoardViewModel.selectedMapMode.setValue(MapMode.RANDOM_WEIGHTS);
		existingViewModel.gameBoardViewModel.startNewGame.setValue(true);
		existingViewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.SELECT_NEXT_EDGE);
		EdgeViewModel secondEdge = existingViewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels
				.stream().skip(1).findFirst().get();
		secondEdge.edgeState.setValue(EdgeState.SELECTED);
		existingViewModel.gameBoardViewModel.selectedEdgeForValidation.setValue(secondEdge);
		existingViewModel.gameBoardViewModel.startNodeForPrim.setValue(
				(VertexViewModel) existingViewModel.gameBoardViewModel.graphViewModel.getValue().vertexViewModels
						.toArray()[0]);

		verify(graphLayoutLoaderMock, times(3)).getRandomlyWeightedGraph(anyInt());
		verifyNoMoreInteractions(graphLayoutLoaderMock, minimumSpanningTreeServiceMock, primAlgorithmMock,
				kruskalAlgorithmMock);
		assertNull(existingViewModel.gameBoardViewModel.selectedEdgeForValidation.getValue());
		assertEquals(EdgeState.UNSELECTED, existingViewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels
				.stream().findFirst().get().edgeState.getValue());
		assertEquals(EdgeState.SELECTED, secondEdge.edgeState.getValue());
		assertEquals(GameBoardState.SELECT_NEXT_EDGE, existingViewModel.gameBoardViewModel.gameBoardState.getValue());
	}

	@Test
	void restartHighscoreTimerIfNeeded_WithGameStateChangedFromGameNotStartedToSelectNextEdge_SetsGameStartMoment() {
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		PrimAlgorithm primAlgorithmMock = mock(PrimAlgorithm.class);
		KruskalAlgorithm kruskalAlgorithmMock = mock(KruskalAlgorithm.class);
		HighscoreService highscoreServiceMock = mock(HighscoreService.class);
		GameModeServiceImpl testee = new GameModeServiceImpl(graphLayoutLoaderMock, minimumSpanningTreeServiceMock,
				primAlgorithmMock, kruskalAlgorithmMock, highscoreServiceMock);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		vertices.add(new VertexModel(120d, 120d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		edges.add(new EdgeModel(vertices.get(1), vertices.get(2), 4));
		edges.add(new EdgeModel(vertices.get(0), vertices.get(2), 4));
		when(graphLayoutLoaderMock.getRandomlyWeightedGraph(anyInt()))
				.thenReturn(new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges)));
		PipeItUpGameViewModel existingViewModel = testee.createStartUpViewModel();

		assertNull(existingViewModel.gameBoardViewModel.gameStartMomentForHighscore.getValue());

		existingViewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.SELECT_NEXT_EDGE);

		assertNotNull(existingViewModel.gameBoardViewModel.gameStartMomentForHighscore.getValue());

	}

	@Test
	void restartHighscoreTimerIfNeeded_WithGameStateChangedFromGameNotStartedToSelectStartNode_SetsGameStartMoment() {
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		PrimAlgorithm primAlgorithmMock = mock(PrimAlgorithm.class);
		KruskalAlgorithm kruskalAlgorithmMock = mock(KruskalAlgorithm.class);
		HighscoreService highscoreServiceMock = mock(HighscoreService.class);
		GameModeServiceImpl testee = new GameModeServiceImpl(graphLayoutLoaderMock, minimumSpanningTreeServiceMock,
				primAlgorithmMock, kruskalAlgorithmMock, highscoreServiceMock);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		vertices.add(new VertexModel(120d, 120d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		edges.add(new EdgeModel(vertices.get(1), vertices.get(2), 4));
		edges.add(new EdgeModel(vertices.get(0), vertices.get(2), 4));
		when(graphLayoutLoaderMock.getRandomlyWeightedGraph(anyInt()))
				.thenReturn(new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges)));
		PipeItUpGameViewModel existingViewModel = testee.createStartUpViewModel();

		assertNull(existingViewModel.gameBoardViewModel.gameStartMomentForHighscore.getValue());

		existingViewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.SELECT_START_NODE);

		assertNotNull(existingViewModel.gameBoardViewModel.gameStartMomentForHighscore.getValue());

	}

	@Test
	void restartHighscoreTimerIfNeeded_WithGameStateChangedFromGameFinishedToSelectStartNode_SetsGameStartMoment() {
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		PrimAlgorithm primAlgorithmMock = mock(PrimAlgorithm.class);
		KruskalAlgorithm kruskalAlgorithmMock = mock(KruskalAlgorithm.class);
		HighscoreService highscoreServiceMock = mock(HighscoreService.class);
		GameModeServiceImpl testee = new GameModeServiceImpl(graphLayoutLoaderMock, minimumSpanningTreeServiceMock,
				primAlgorithmMock, kruskalAlgorithmMock, highscoreServiceMock);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		vertices.add(new VertexModel(120d, 120d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		edges.add(new EdgeModel(vertices.get(1), vertices.get(2), 4));
		edges.add(new EdgeModel(vertices.get(0), vertices.get(2), 4));
		when(graphLayoutLoaderMock.getRandomlyWeightedGraph(anyInt()))
				.thenReturn(new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges)));
		PipeItUpGameViewModel existingViewModel = testee.createStartUpViewModel();
		existingViewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.GAME_FINISHED);

		assertNull(existingViewModel.gameBoardViewModel.gameStartMomentForHighscore.getValue());

		existingViewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.SELECT_START_NODE);

		assertNotNull(existingViewModel.gameBoardViewModel.gameStartMomentForHighscore.getValue());

	}

	@Test
	void restartHighscoreTimerIfNeeded_WithGameStateChangedFromGameFinishedToSelectNextEdge_SetsGameStartMoment() {
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		PrimAlgorithm primAlgorithmMock = mock(PrimAlgorithm.class);
		KruskalAlgorithm kruskalAlgorithmMock = mock(KruskalAlgorithm.class);
		HighscoreService highscoreServiceMock = mock(HighscoreService.class);
		GameModeServiceImpl testee = new GameModeServiceImpl(graphLayoutLoaderMock, minimumSpanningTreeServiceMock,
				primAlgorithmMock, kruskalAlgorithmMock, highscoreServiceMock);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		vertices.add(new VertexModel(120d, 120d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		edges.add(new EdgeModel(vertices.get(1), vertices.get(2), 4));
		edges.add(new EdgeModel(vertices.get(0), vertices.get(2), 4));
		when(graphLayoutLoaderMock.getRandomlyWeightedGraph(anyInt()))
				.thenReturn(new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges)));
		PipeItUpGameViewModel existingViewModel = testee.createStartUpViewModel();
		existingViewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.GAME_FINISHED);

		assertNull(existingViewModel.gameBoardViewModel.gameStartMomentForHighscore.getValue());

		existingViewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.SELECT_NEXT_EDGE);

		assertNotNull(existingViewModel.gameBoardViewModel.gameStartMomentForHighscore.getValue());
	}

	@Test
	void restartHighscoreTimerIfNeeded_WithGameStateChangedFromGameFinishedToGameNotStarted_DoesNotSetGameStart() {
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		PrimAlgorithm primAlgorithmMock = mock(PrimAlgorithm.class);
		KruskalAlgorithm kruskalAlgorithmMock = mock(KruskalAlgorithm.class);
		HighscoreService highscoreServiceMock = mock(HighscoreService.class);
		GameModeServiceImpl testee = new GameModeServiceImpl(graphLayoutLoaderMock, minimumSpanningTreeServiceMock,
				primAlgorithmMock, kruskalAlgorithmMock, highscoreServiceMock);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		vertices.add(new VertexModel(120d, 120d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		edges.add(new EdgeModel(vertices.get(1), vertices.get(2), 4));
		edges.add(new EdgeModel(vertices.get(0), vertices.get(2), 4));
		when(graphLayoutLoaderMock.getRandomlyWeightedGraph(anyInt()))
				.thenReturn(new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges)));
		PipeItUpGameViewModel existingViewModel = testee.createStartUpViewModel();
		existingViewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.GAME_FINISHED);

		assertNull(existingViewModel.gameBoardViewModel.gameStartMomentForHighscore.getValue());

		existingViewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.GAME_NOT_STARTED);

		assertNull(existingViewModel.gameBoardViewModel.gameStartMomentForHighscore.getValue());
	}

	@Test
	void setStartNodeForPrim_WithNodeBeeingNull_DoesNothing() {
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		PrimAlgorithm primAlgorithmMock = mock(PrimAlgorithm.class);
		KruskalAlgorithm kruskalAlgorithmMock = mock(KruskalAlgorithm.class);
		HighscoreService highscoreServiceMock = mock(HighscoreService.class);
		GameModeServiceImpl testee = new GameModeServiceImpl(graphLayoutLoaderMock, minimumSpanningTreeServiceMock,
				primAlgorithmMock, kruskalAlgorithmMock, highscoreServiceMock);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		vertices.add(new VertexModel(120d, 120d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		edges.add(new EdgeModel(vertices.get(1), vertices.get(2), 4));
		edges.add(new EdgeModel(vertices.get(0), vertices.get(2), 4));
		when(graphLayoutLoaderMock.getRandomlyWeightedGraph(anyInt()))
				.thenReturn(new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges)));
		PipeItUpGameViewModel existingViewModel = new PipeItUpGameViewModel();
		testee.loadRandomWeightedGraph(existingViewModel);
		existingViewModel.gameBoardViewModel.gameMode.setValue(GameMode.PRIM);
		existingViewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.SELECT_START_NODE);
		existingViewModel.gameBoardViewModel.startNodeForPrim.setValue(null);

		testee.setStartNodeForPrim(existingViewModel);

		assertEquals(existingViewModel.gameBoardViewModel.gameBoardState.getValue(), GameBoardState.SELECT_START_NODE);
	}

	@Test
	void setStartNodeForPrim_WithStartNodeSetButNotInStateSelectStartNode_DoesNothing() {
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		PrimAlgorithm primAlgorithmMock = mock(PrimAlgorithm.class);
		KruskalAlgorithm kruskalAlgorithmMock = mock(KruskalAlgorithm.class);
		HighscoreService highscoreServiceMock = mock(HighscoreService.class);
		GameModeServiceImpl testee = new GameModeServiceImpl(graphLayoutLoaderMock, minimumSpanningTreeServiceMock,
				primAlgorithmMock, kruskalAlgorithmMock, highscoreServiceMock);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		vertices.add(new VertexModel(120d, 120d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		edges.add(new EdgeModel(vertices.get(1), vertices.get(2), 4));
		edges.add(new EdgeModel(vertices.get(0), vertices.get(2), 4));
		when(graphLayoutLoaderMock.getRandomlyWeightedGraph(anyInt()))
				.thenReturn(new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges)));
		PipeItUpGameViewModel existingViewModel = new PipeItUpGameViewModel();
		testee.loadRandomWeightedGraph(existingViewModel);
		existingViewModel.gameBoardViewModel.gameMode.setValue(GameMode.PRIM);
		existingViewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.SELECT_NEXT_EDGE);
		existingViewModel.gameBoardViewModel.startNodeForPrim.setValue(
				(VertexViewModel) existingViewModel.gameBoardViewModel.graphViewModel.getValue().vertexViewModels
						.toArray()[0]);

		testee.setStartNodeForPrim(existingViewModel);

		assertEquals(existingViewModel.gameBoardViewModel.gameBoardState.getValue(), GameBoardState.SELECT_NEXT_EDGE);
	}

	@Test
	void setStartNodeForPrim_WithStartNodeSetAndInStateSelectStartNode_SetsGameStateToSelectNextEdge() {
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		PrimAlgorithm primAlgorithmMock = mock(PrimAlgorithm.class);
		KruskalAlgorithm kruskalAlgorithmMock = mock(KruskalAlgorithm.class);
		HighscoreService highscoreServiceMock = mock(HighscoreService.class);
		GameModeServiceImpl testee = new GameModeServiceImpl(graphLayoutLoaderMock, minimumSpanningTreeServiceMock,
				primAlgorithmMock, kruskalAlgorithmMock, highscoreServiceMock);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		vertices.add(new VertexModel(120d, 120d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		edges.add(new EdgeModel(vertices.get(1), vertices.get(2), 4));
		edges.add(new EdgeModel(vertices.get(0), vertices.get(2), 4));
		when(graphLayoutLoaderMock.getRandomlyWeightedGraph(anyInt()))
				.thenReturn(new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges)));
		PipeItUpGameViewModel existingViewModel = new PipeItUpGameViewModel();
		testee.loadRandomWeightedGraph(existingViewModel);
		existingViewModel.gameBoardViewModel.gameMode.setValue(GameMode.PRIM);
		existingViewModel.gameBoardViewModel.gameBoardState.setValue(GameBoardState.SELECT_START_NODE);
		existingViewModel.gameBoardViewModel.startNodeForPrim.setValue(
				(VertexViewModel) existingViewModel.gameBoardViewModel.graphViewModel.getValue().vertexViewModels
						.toArray()[0]);

		testee.setStartNodeForPrim(existingViewModel);

		assertEquals(existingViewModel.gameBoardViewModel.gameBoardState.getValue(), GameBoardState.SELECT_NEXT_EDGE);
	}

	@Test
	void showSolution_WithSmallSpanningTree_SetsSPanningTreeEdges() {
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		PrimAlgorithm primAlgorithmMock = mock(PrimAlgorithm.class);
		KruskalAlgorithm kruskalAlgorithmMock = mock(KruskalAlgorithm.class);
		HighscoreService highscoreServiceMock = mock(HighscoreService.class);
		GameModeServiceImpl testee = new GameModeServiceImpl(graphLayoutLoaderMock, minimumSpanningTreeServiceMock,
				primAlgorithmMock, kruskalAlgorithmMock, highscoreServiceMock);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		vertices.add(new VertexModel(120d, 120d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		edges.add(new EdgeModel(vertices.get(1), vertices.get(2), 5));
		edges.add(new EdgeModel(vertices.get(0), vertices.get(2), 5));
		when(graphLayoutLoaderMock.getRandomlyWeightedGraph(anyInt()))
				.thenReturn(new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges)));
		when(kruskalAlgorithmMock.isEdgeValidPick(any(), any())).thenReturn(true).thenReturn(false).thenReturn(false)
				.thenReturn(true).thenReturn(false);
		when(minimumSpanningTreeServiceMock.isMspCompleted(any())).thenReturn(false).thenReturn(false).thenReturn(true);
		PipeItUpGameViewModel existingViewModel = testee.createStartUpViewModel();
		testee.loadRandomWeightedGraph(existingViewModel);
		existingViewModel.gameBoardViewModel.showSolution.setValue(true);

		assertEquals(2, existingViewModel.gameBoardViewModel.graphViewModel.getValue().edgeViewModels.stream()
				.filter(e -> e.edgeState.getValue() == EdgeState.SELECTED).count());
	}

	@Test
	void createStartUpViewModel_WithOneHighscoreEntry_AddsEntryToViewModel() {
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		PrimAlgorithm primAlgorithmMock = mock(PrimAlgorithm.class);
		KruskalAlgorithm kruskalAlgorithmMock = mock(KruskalAlgorithm.class);
		HighscoreService highscoreServiceMock = mock(HighscoreService.class);
		GameModeServiceImpl testee = new GameModeServiceImpl(graphLayoutLoaderMock, minimumSpanningTreeServiceMock,
				primAlgorithmMock, kruskalAlgorithmMock, highscoreServiceMock);
		HighscoreEntryModel highscoreEntry = new HighscoreEntryModel("testUser", 123, 0);
		ArrayList<HighscoreEntryModel> highscoreEntries = new ArrayList<>();
		highscoreEntries.add(highscoreEntry);
		when(highscoreServiceMock.getHighscoreEntries()).thenReturn(highscoreEntries);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		vertices.add(new VertexModel(120d, 120d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		edges.add(new EdgeModel(vertices.get(1), vertices.get(2), 5));
		edges.add(new EdgeModel(vertices.get(0), vertices.get(2), 5));
		when(graphLayoutLoaderMock.getRandomlyWeightedGraph(anyInt()))
				.thenReturn(new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges)));

		PipeItUpGameViewModel existingViewModel = testee.createStartUpViewModel();

		assertEquals(1, existingViewModel.touchViewModel.highscoreEntries.size());
		assertEquals("testUser", existingViewModel.touchViewModel.highscoreEntries.get(0).userName.get());
		assertEquals(123, existingViewModel.touchViewModel.highscoreEntries.get(0).highscoreInSeconds.get());
		assertEquals(GameMode.valueOf(0).get(),
				existingViewModel.touchViewModel.highscoreEntries.get(0).gameMode.getValue());
	}

	@Test
	void saveHighscoreEntry_WithAHighscoreEntry_CallsHighscoreService() {
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		PrimAlgorithm primAlgorithmMock = mock(PrimAlgorithm.class);
		KruskalAlgorithm kruskalAlgorithmMock = mock(KruskalAlgorithm.class);
		HighscoreService highscoreServiceMock = mock(HighscoreService.class);
		GameModeServiceImpl testee = new GameModeServiceImpl(graphLayoutLoaderMock, minimumSpanningTreeServiceMock,
				primAlgorithmMock, kruskalAlgorithmMock, highscoreServiceMock);
		HighscoreEntryModel highscoreEntry = new HighscoreEntryModel("testUser", 123, 0);
		ArrayList<HighscoreEntryModel> highscoreEntries = new ArrayList<>();
		highscoreEntries.add(highscoreEntry);
		when(highscoreServiceMock.getHighscoreEntries()).thenReturn(highscoreEntries);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		vertices.add(new VertexModel(120d, 120d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		edges.add(new EdgeModel(vertices.get(1), vertices.get(2), 5));
		edges.add(new EdgeModel(vertices.get(0), vertices.get(2), 5));
		when(graphLayoutLoaderMock.getRandomlyWeightedGraph(anyInt()))
				.thenReturn(new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges)));
		PipeItUpGameViewModel existingViewModel = testee.createStartUpViewModel();

		existingViewModel.touchViewModel.newHighscoreEntryToSave
				.setValue(new HighscoreEntryViewModel("testUser", 100f, GameMode.PRIM));

		verify(graphLayoutLoaderMock, times(2)).getRandomlyWeightedGraph(anyInt());
		verify(highscoreServiceMock, times(1)).saveHighscoreEntry(any());
		verifyNoMoreInteractions(graphLayoutLoaderMock, minimumSpanningTreeServiceMock, primAlgorithmMock,
				kruskalAlgorithmMock);
	}
}
