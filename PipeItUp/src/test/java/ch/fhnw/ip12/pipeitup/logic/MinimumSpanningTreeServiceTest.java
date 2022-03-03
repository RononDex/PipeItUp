package ch.fhnw.ip12.pipeitup.logic;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;
import ch.fhnw.ip12.pipeitup.logic.Models.VertexModel;
import ch.fhnw.ip12.pipeitup.logic.utils.GraphLayoutBuilder;

class MinimumSpanningTreeServiceTest {

	@Test
	void getEdgesConnectedToVertex_WithAVertexThatHasOneEdge_ReturnsOneEdge() {
		// Arrange
		VertexModel v1 = new VertexModel(0, 1);
		GraphLayoutModel graph = new GraphLayoutBuilder()
				.withVertex(v1)
				.withVertex(0, 2)
				.withVertex(1, 1)
				.withVertex(1, 2)
				.withEdge(0, 1, 1)
				.withEdge(2, 3, 1)
				.build();
		MinimumSpanningTreeServiceImpl testee = new MinimumSpanningTreeServiceImpl();

		// Act
		Set<EdgeModel> actual = testee.getEdgesConnectedToVertex(graph, v1);

		// Assert
		assertEquals(actual.size(), 1);
		assertEquals(actual.stream().findFirst().get().getWeight(), 1);
		assertEquals(actual.stream().findFirst().get().getVertex1().getPositionX(), 0);
		assertEquals(actual.stream().findFirst().get().getVertex1().getPositionY(), 1);
		assertEquals(actual.stream().findFirst().get().getVertex2().getPositionX(), 0);
		assertEquals(actual.stream().findFirst().get().getVertex2().getPositionY(), 2);
	}

	@Test
	void getEdgesConnectedToVertex_WithAVertexThatHasNoEdges_ReturnsEmptySet() {
		// Arrange
		VertexModel v1 = new VertexModel(0, 1);
		GraphLayoutModel graph = new GraphLayoutBuilder()
				.withVertex(v1)
				.withVertex(0, 2)
				.withVertex(1, 1)
				.withVertex(1, 2)
				.withEdge(2, 3, 1)
				.build();
		MinimumSpanningTreeServiceImpl testee = new MinimumSpanningTreeServiceImpl();

		// Act
		Set<EdgeModel> actual = testee.getEdgesConnectedToVertex(graph, v1);

		// Assert
		assertEquals(actual.size(), 0);
	}

	@Test
	void getEdgesConnectedToVertex_WithAVertexNotBelongingToGraph_ReturnsEmptySet() {
		// Arrange
		VertexModel v1 = new VertexModel(0, 1);
		GraphLayoutModel graph = new GraphLayoutBuilder()
				.withVertex(0, 1)
				.withVertex(0, 2)
				.withVertex(1, 1)
				.withVertex(1, 2)
				.withEdge(2, 3, 1)
				.build();
		MinimumSpanningTreeServiceImpl testee = new MinimumSpanningTreeServiceImpl();

		// Act
		Set<EdgeModel> actual = testee.getEdgesConnectedToVertex(graph, v1);

		// Assert
		assertEquals(actual.size(), 0);
	}

    @Test
    void usedWeightsSum_WithAllUnusedEdges_ReturnsZero() {
        GraphLayoutModel graph = new GraphLayoutBuilder()
            .withVertex(0, 1)
            .withVertex(0, 2)
            .withVertex(1, 1)
            .withVertex(1, 2)
            .withVertex(2, 1)
            .withVertex(2, 2)
            .withEdge(0, 1, 1)
            .withEdge(1, 2, 3)
            .withEdge(2, 3, 2)
            .withEdge(3, 4, 4)
            .withEdge(4, 5, 1)
            .withEdge(5, 0, 1)
            .build();
        MinimumSpanningTreeServiceImpl testee = new MinimumSpanningTreeServiceImpl();

        int actual = testee.usedWeightsSum(graph);

        assertEquals(0, actual);
    }

}