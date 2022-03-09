package ch.fhnw.ip12.pipeitup.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;
import ch.fhnw.ip12.pipeitup.logic.Models.VertexModel;
import ch.fhnw.ip12.pipeitup.logic.utils.GraphLayoutBuilder;

class MinimumSpanningTreeServiceTest {

    @Test
    void usedWeightsSum_WithAllUnusedEdges_ReturnsZero() {
        GraphLayoutModel graph = new GraphLayoutBuilder()
            .withVertex(0, 1)
            .withVertex(0, 2)
            .withVertex(1, 1)
            .withVertex(1, 2)
            .withVertex(2, 1)
            .withVertex(2, 2)
            .withUnusedEdge(0, 1, 1)
            .withUnusedEdge(1, 2, 3)
            .withUnusedEdge(2, 3, 2)
            .withUnusedEdge(3, 4, 4)
            .withUnusedEdge(4, 5, 1)
            .withUnusedEdge(5, 0, 1)
            .build();
        MinimumSpanningTreeServiceImpl testee = new MinimumSpanningTreeServiceImpl();

        int actual = testee.usedWeightsSum(graph);

        assertEquals(0, actual);
    }

    @Test
    void usedWeightsSum_WithSomeUsedEdges_ReturnsSum() {
        GraphLayoutModel graph = new GraphLayoutBuilder()
            .withVertex(0, 1)
            .withVertex(0, 2)
            .withVertex(1, 1)
            .withVertex(1, 2)
            .withVertex(2, 1)
            .withVertex(2, 2)
            .withUsedEdge(0, 1, 1)
            .withUnusedEdge(1, 2, 3)
            .withUsedEdge(2, 3, 2)
            .withUnusedEdge(3, 4, 4)
            .withUsedEdge(4, 5, 1)
            .withUnusedEdge(5, 0, 1)
            .build();
        MinimumSpanningTreeServiceImpl testee = new MinimumSpanningTreeServiceImpl();

        int actual = testee.usedWeightsSum(graph);

        assertEquals(4, actual);
    }

	@Test
	void canEdgeBeUsed_WithAlreadyUsedEdge_ReturnsFalse() {
        GraphLayoutModel graph = new GraphLayoutBuilder()
            .withVertex(0, 1)
            .withVertex(0, 2)
            .withVertex(1, 1)
            .withVertex(1, 2)
            .withVertex(2, 1)
            .withVertex(2, 2)
            .withUsedEdge(0, 1, 1)
            .withUnusedEdge(1, 2, 3)
            .withUsedEdge(2, 3, 2)
            .withUnusedEdge(3, 4, 4)
            .withUsedEdge(4, 5, 1)
            .withUnusedEdge(5, 0, 1)
            .build();
		EdgeModel usedEdge = graph.getEdges().stream().filter(edge -> edge.isUsed()).findFirst().get();
        MinimumSpanningTreeServiceImpl testee = new MinimumSpanningTreeServiceImpl();

		boolean actual = testee.canEdgeBeUsed(graph, usedEdge);

		assertFalse(actual);
	}

	@Test
	void canEdgeBeUsed_WithUnusedEdgeThatCreatesLoop_ReturnsFalse() {
		VertexModel vertex1 = new VertexModel(0, 1);
		VertexModel vertex2 = new VertexModel(0, 2);
		EdgeModel edge12 = new EdgeModel(vertex1, vertex2, 1);
        GraphLayoutModel graph = new GraphLayoutBuilder()
            .withVertex(vertex1)
            .withVertex(vertex2)
            .withVertex(1, 1)
            .withVertex(1, 2)
            .withVertex(2, 1)
            .withVertex(2, 2)
            .withVertex(3, 1)
            .withEdge(edge12)
            .withUsedEdge(1, 2, 3)
            .withUsedEdge(2, 3, 2)
            .withUsedEdge(3, 4, 4)
            .withUsedEdge(4, 5, 1)
            .withUsedEdge(5, 0, 1)
            .withUnusedEdge(6, 0, 1)
            .withUnusedEdge(5, 2, 1)
            .build();
        MinimumSpanningTreeServiceImpl testee = new MinimumSpanningTreeServiceImpl();

		boolean actual = testee.canEdgeBeUsed(graph, edge12);

		assertFalse(actual);
	}

	@Test
	void canEdgeBeUsed_WithUnusedEdgeThatDoesNotCreateLoop_ReturnsTrue() {
		VertexModel vertex1 = new VertexModel(0, 1);
		VertexModel vertex2 = new VertexModel(0, 2);
		EdgeModel edge12 = new EdgeModel(vertex1, vertex2, 1);
        GraphLayoutModel graph = new GraphLayoutBuilder()
            .withVertex(vertex1)
            .withVertex(vertex2)
            .withVertex(1, 1)
            .withVertex(1, 2)
            .withVertex(2, 1)
            .withVertex(2, 2)
            .withVertex(3, 1)
            .withEdge(edge12)
            .withUsedEdge(1, 2, 3)
            .withUsedEdge(2, 3, 2)
            .withUsedEdge(3, 4, 4)
            .withUsedEdge(4, 5, 1)
            .withUnusedEdge(5, 0, 1)
            .withUnusedEdge(6, 0, 1)
            .withUnusedEdge(5, 2, 1)
            .build();
        MinimumSpanningTreeServiceImpl testee = new MinimumSpanningTreeServiceImpl();

		boolean actual = testee.canEdgeBeUsed(graph, edge12);

		assertTrue(actual);
	}

	@Test
	void isMspCompleted_WithNoUsedEdges_ReturnsFalse() {
		GraphLayoutModel graph = new GraphLayoutBuilder()
				.withVertex(0, 1)
				.withVertex(0, 2)
				.withVertex(1, 1)
				.withVertex(1, 2)
				.withUnusedEdge(0, 1, 1)
				.withUnusedEdge(2, 3, 1)
				.build();
		MinimumSpanningTreeServiceImpl testee = new MinimumSpanningTreeServiceImpl();

		boolean actual = testee.isMspCompleted(graph);

		assertFalse(actual);
	}

	@Test
	void isMspCompleted_WithEveryVertexUsedButMspNotConnected_ReturnsFalse() {
		GraphLayoutModel graph = new GraphLayoutBuilder()
				.withVertex(0, 1)
				.withVertex(0, 2)
				.withVertex(1, 1)
				.withVertex(1, 2)
				.withUnusedEdge(0, 1, 1)
				.withUsedEdge(1, 2, 1)
				.withUnusedEdge(2, 3, 1)
				.withUsedEdge(3, 0, 1)
				.build();
		MinimumSpanningTreeServiceImpl testee = new MinimumSpanningTreeServiceImpl();

		boolean actual = testee.isMspCompleted(graph);

		assertFalse(actual);
	}

	@Test
	void isMspCompleted_WithOneVertexUnused_ReturnsFalse() {
		GraphLayoutModel graph = new GraphLayoutBuilder()
				.withVertex(0, 1)
				.withVertex(0, 2)
				.withVertex(1, 1)
				.withVertex(1, 2)
				.withUnusedEdge(0, 1, 1)
				.withUnusedEdge(1, 2, 1)
				.withUsedEdge(2, 3, 1)
				.withUsedEdge(3, 0, 1)
				.build();
		MinimumSpanningTreeServiceImpl testee = new MinimumSpanningTreeServiceImpl();

		boolean actual = testee.isMspCompleted(graph);

		assertFalse(actual);
	}

	@Test
	void isMspCompleted_WithAllVerticesUsedAndConnected_ReturnsTrue() {
		GraphLayoutModel graph = new GraphLayoutBuilder()
				.withVertex(0, 1)
				.withVertex(0, 2)
				.withVertex(1, 1)
				.withVertex(1, 2)
				.withUnusedEdge(0, 1, 1)
				.withUsedEdge(1, 2, 1)
				.withUsedEdge(2, 3, 1)
				.withUsedEdge(3, 0, 1)
				.build();
		MinimumSpanningTreeServiceImpl testee = new MinimumSpanningTreeServiceImpl();

		boolean actual = testee.isMspCompleted(graph);

		assertTrue(actual);
	}
}