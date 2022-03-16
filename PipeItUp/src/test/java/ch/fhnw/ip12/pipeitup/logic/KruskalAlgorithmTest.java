package ch.fhnw.ip12.pipeitup.logic;

import org.junit.jupiter.api.Test;

import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;
import ch.fhnw.ip12.pipeitup.logic.Models.VertexModel;
import ch.fhnw.ip12.pipeitup.logic.utils.GraphLayoutBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;

class KruskalAlgorithmTest {

	@Test
	void isEdgeValidPick_WithEdgeThatCanNotBeUsed_ReturnsFalse() {
		// Arrange
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		when(minimumSpanningTreeServiceMock.canEdgeBeUsed(any(), any())).thenReturn(false);
		KruskalAlgorithmImpl testee = new KruskalAlgorithmImpl(minimumSpanningTreeServiceMock);
		VertexModel v1 = new VertexModel(0, 1);
		VertexModel v2 = new VertexModel(0, 2);
		EdgeModel e1 = new EdgeModel(v1, v2, 1);
		GraphLayoutModel graph = new GraphLayoutBuilder().withVertex(v1).withVertex(v2).withEdge(e1).build();

		// Act
		boolean actual = testee.isEdgeValidPick(graph, e1);

		// Assert
		assertFalse(actual);
	}

	@Test
	void isEdgeValidPick_WithEdgeThatCanBeUsedAndIsOnlyEdge_ReturnsTrue() {
		// Arrange
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		when(minimumSpanningTreeServiceMock.canEdgeBeUsed(any(), any())).thenReturn(true);
		KruskalAlgorithmImpl testee = new KruskalAlgorithmImpl(minimumSpanningTreeServiceMock);
		VertexModel v1 = new VertexModel(0, 1);
		VertexModel v2 = new VertexModel(0, 2);
		EdgeModel e1 = new EdgeModel(v1, v2, 1);
		GraphLayoutModel graph = new GraphLayoutBuilder().withVertex(v1).withVertex(v2).withEdge(e1).build();

		// Act
		boolean actual = testee.isEdgeValidPick(graph, e1);

		// Assert
		assertTrue(actual);
	}

	@Test
	void isEdgeValidPick_WithNoEdgesSelectedYet_ReturnsTrueForAllEdgesWithMinimumWeight() {
		// Arrange
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		when(minimumSpanningTreeServiceMock.canEdgeBeUsed(any(), any())).thenReturn(true);
		KruskalAlgorithmImpl testee = new KruskalAlgorithmImpl(minimumSpanningTreeServiceMock);
		VertexModel v1 = new VertexModel(0, 1);
		VertexModel v2 = new VertexModel(0, 2);
		VertexModel v3 = new VertexModel(1, 1);
		VertexModel v4 = new VertexModel(1, 2);
		EdgeModel e1 = new EdgeModel(v1, v2, 2);
		EdgeModel e2 = new EdgeModel(v1, v3, 4);
		EdgeModel e3 = new EdgeModel(v1, v4, 3);
		EdgeModel e4 = new EdgeModel(v2, v4, 1);
		EdgeModel e5 = new EdgeModel(v3, v4, 1);
		GraphLayoutModel graph = new GraphLayoutBuilder().withVertex(v1).withVertex(v2).withVertex(v3).withVertex(v4)
				.withEdge(e1).withEdge(e2).withEdge(e3).withEdge(e4).withEdge(e5).build();
		HashMap<EdgeModel, Boolean> edgesTestResult = new HashMap<>();
		edgesTestResult.put(e1, false);
		edgesTestResult.put(e2, false);
		edgesTestResult.put(e3, false);
		edgesTestResult.put(e4, false);
		edgesTestResult.put(e5, false);

		// Act
		for (EdgeModel edge : edgesTestResult.keySet()) {
			boolean actual = testee.isEdgeValidPick(graph, edge);
			edgesTestResult.put(edge, actual);
		}

		// Assert
		for (EdgeModel edge : edgesTestResult.keySet()) {
			if (edge != e4 && edge != e5) {
				assertFalse(edgesTestResult.get(edge));
			} else {
				assertTrue(edgesTestResult.get(edge));
			}
		}
	}

	@Test
	void isEdgeValidPick_WithSomeEdgesSelectedYet_ReturnsTrueForAllUsableRemainingEdgesWithMinimumWeight() {
		// Arrange
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		when(minimumSpanningTreeServiceMock.canEdgeBeUsed(any(), any())).thenReturn(true);
		KruskalAlgorithmImpl testee = new KruskalAlgorithmImpl(minimumSpanningTreeServiceMock);
		VertexModel v1 = new VertexModel(0, 1);
		VertexModel v2 = new VertexModel(0, 2);
		VertexModel v3 = new VertexModel(1, 1);
		VertexModel v4 = new VertexModel(1, 2);
		VertexModel v5 = new VertexModel(2, 1);
		VertexModel v6 = new VertexModel(2, 2);
		EdgeModel e1 = new EdgeModel(v1, v2, 2);
		EdgeModel e2 = new EdgeModel(v1, v3, 4);
		EdgeModel e3 = new EdgeModel(v1, v4, 3);
		EdgeModel e4 = new EdgeModel(v2, v4, 4);
		EdgeModel e5 = new EdgeModel(v3, v4, 1);
		EdgeModel e6 = new EdgeModel(v4, v5, 3);
		EdgeModel e7 = new EdgeModel(v4, v6, 1);
		EdgeModel e8 = new EdgeModel(v6, v2, 1);
		GraphLayoutModel graph = new GraphLayoutBuilder()
				.withVertex(v1)
				.withVertex(v2)
				.withVertex(v3)
				.withVertex(v4)
				.withVertex(v5)
				.withVertex(v6)
				.withUsedEdge(e1)
				.withEdge(e2)
				.withEdge(e3)
				.withEdge(e4)
				.withEdge(e5)
				.withEdge(e6)
				.withEdge(e7)
				.withUsedEdge(e8)
				.build();
		HashMap<EdgeModel, Boolean> edgesTestResult = new HashMap<>();
		edgesTestResult.put(e1, false);
		edgesTestResult.put(e2, false);
		edgesTestResult.put(e3, false);
		edgesTestResult.put(e4, false);
		edgesTestResult.put(e5, false);
		edgesTestResult.put(e6, false);
		edgesTestResult.put(e7, false);
		edgesTestResult.put(e8, false);
		when(minimumSpanningTreeServiceMock.canEdgeBeUsed(any(), eq(e1))).thenReturn(true);
		when(minimumSpanningTreeServiceMock.canEdgeBeUsed(any(), eq(e2))).thenReturn(true);
		when(minimumSpanningTreeServiceMock.canEdgeBeUsed(any(), eq(e3))).thenReturn(true);
		when(minimumSpanningTreeServiceMock.canEdgeBeUsed(any(), eq(e4))).thenReturn(true);
		when(minimumSpanningTreeServiceMock.canEdgeBeUsed(any(), eq(e5))).thenReturn(true);
		when(minimumSpanningTreeServiceMock.canEdgeBeUsed(any(), eq(e6))).thenReturn(true);
		when(minimumSpanningTreeServiceMock.canEdgeBeUsed(any(), eq(e7))).thenReturn(false);
		when(minimumSpanningTreeServiceMock.canEdgeBeUsed(any(), eq(e8))).thenReturn(true);

		// Act
		for (EdgeModel edge : edgesTestResult.keySet()) {
			boolean actual = testee.isEdgeValidPick(graph, edge);
			edgesTestResult.put(edge, actual);
		}

		// Assert
		for (EdgeModel edge : edgesTestResult.keySet()) {
			if (edge != e8 && edge != e5) {
				assertFalse(edgesTestResult.get(edge));
			} else {
				assertTrue(edgesTestResult.get(edge));
			}
		}
	}

	@Test
	void isEdgeValidPick_WithCompletedMSP_AlwaysReturnsFalse() {
		// Arrange
		MinimumSpanningTreeService minimumSpanningTreeServiceMock = mock(MinimumSpanningTreeService.class);
		KruskalAlgorithmImpl testee = new KruskalAlgorithmImpl(minimumSpanningTreeServiceMock);
		VertexModel v1 = new VertexModel(0, 1);
		VertexModel v2 = new VertexModel(0, 2);
		VertexModel v3 = new VertexModel(1, 1);
		VertexModel v4 = new VertexModel(1, 2);
		EdgeModel e1 = new EdgeModel(v1, v2, 2);
		EdgeModel e2 = new EdgeModel(v1, v3, 4);
		EdgeModel e3 = new EdgeModel(v1, v4, 3);
		EdgeModel e4 = new EdgeModel(v2, v4, 1);
		EdgeModel e5 = new EdgeModel(v3, v4, 1);
		GraphLayoutModel graph = new GraphLayoutBuilder().withVertex(v1).withVertex(v2).withVertex(v3).withVertex(v4)
				.withEdge(e1).withEdge(e2).withEdge(e3).withEdge(e4).withEdge(e5).build();
		HashMap<EdgeModel, Boolean> edgesTestResult = new HashMap<>();
		edgesTestResult.put(e1, false);
		edgesTestResult.put(e2, false);
		edgesTestResult.put(e3, false);
		edgesTestResult.put(e4, false);
		edgesTestResult.put(e5, false);
		when(minimumSpanningTreeServiceMock.isMspCompleted(any())).thenReturn(true);
		when(minimumSpanningTreeServiceMock.canEdgeBeUsed(any(), any())).thenReturn(true);

		// Act
		for (EdgeModel edge : edgesTestResult.keySet()) {
			boolean actual = testee.isEdgeValidPick(graph, edge);
			edgesTestResult.put(edge, actual);
		}

		// Assert
		for (EdgeModel edge : edgesTestResult.keySet()) {
			assertFalse(edgesTestResult.get(edge));
		}
	}

}