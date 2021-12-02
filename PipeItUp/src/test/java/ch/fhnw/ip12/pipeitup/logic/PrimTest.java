package ch.fhnw.ip12.pipeitup.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimTest {

	@Test
	void isNextEdge() {

		// useCase:
		int[][] incidenceMatrix = {
				{1, 6, 7, 0, 0},
				{1, 0, 0, 4, 0},
				{0, 6, 0, 0, 3},
				{0, 0, 7, 4, 3}
		};
		Graph graph = Graph.fromIncidentMatrix(incidenceMatrix);
		MinimumSpanningTreeAlgorithm prim = new Prim(graph, new Vertex()); // TODO: fix this
		Edge[] edges = graph.getEdges().toArray(new Edge[0]);

		System.out.println(edges[0].getWeight());
		System.out.println(prim.EdgeIsUsable(edges[0]));
		assertTrue(prim.isNextEdge(edges[0])); // get edge1 (all should be true really)
		assertTrue(prim.isNextEdge(edges[1])); // get edge1 (all should be true really)
	}

	@Test
	void allVerticesUsed() {
	}

	@Test
	void sum() {
	}

	@Test
	void nextPossibleEdges() {
	}
}