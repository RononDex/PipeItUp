package ch.fhnw.ip12.pipeitup.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import ch.fhnw.ip12.pipeitup.logic.GraphLayoutLoader;
import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;
import ch.fhnw.ip12.pipeitup.logic.Models.VertexModel;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;

/**
 * ViewModelServiceImplTests
 */
class ViewModelServiceImplTest {

	@Test
	void createStartUpViewModel_WithNoParameters_ReturnsNewViewModelInStartUpState() {
		// Arrange
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		ViewModelServiceImpl testee = new ViewModelServiceImpl(graphLayoutLoaderMock);
		ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
		vertices.add(new VertexModel(100d, 100d));
		vertices.add(new VertexModel(110d, 110d));
		ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
		edges.add(new EdgeModel(vertices.get(0), vertices.get(1), 5));
		when(graphLayoutLoaderMock.getRandomlyWeightedGraph(anyInt()))
				.thenReturn(new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges)));

		// Act
		PipeItUpGameViewModel actual = testee.createStartUpViewModel();

		// Assert
		assertNotNull(actual);
		assertNotNull(actual.touchViewModel);
		assertNotNull(actual.gameBoardViewModel);
		assertEquals(0, actual.touchViewModel.currentSum.get());
		assertEquals(0, actual.touchViewModel.currentSecondsPassed.get());
		assertEquals(2,
				actual.gameBoardViewModel.getGraphViewModel().getValue().getVertexViewModels().getValue().size());
		assertEquals(1, actual.gameBoardViewModel.getGraphViewModel().getValue().getEdgeViewModels().getValue().size());
		assertEquals(5,
				actual.gameBoardViewModel.getGraphViewModel().getValue().getEdgeViewModels().getValue().stream()
						.collect(Collectors.toList()).get(0).weight.get());
		assertEquals(TouchScene.START_SCREEN, actual.touchViewModel.currentScene.get());
	}

}
