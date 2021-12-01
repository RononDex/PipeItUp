package ch.fhnw.ip12.pipeitup.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KruskalTest {

	@Test
	void edgeIsUsable() {

	}

	@Test
	void completed() {
	}

	@Test
	void sum() {
	}

	@Test
	void addEdgeToTree() {
	}

	@Test
	void getUnusedEdges() {
	}

	@Test
	void isNextEdge() {

		// useCase:
		int[][] incidenceMatrix = {
				{1, 6, 1, 0, 0},
				{1, 0, 0, 4, 0},
				{0, 6, 0, 0, 3},
				{0, 0, 1, 4, 3}
		};
		Graph graph = new Graph(incidenceMatrix);
		MinimumSpanningTree kruskal = new Kruskal(graph);
		Edge[] edges = graph.getEdges().toArray(new Edge[0]);

		System.out.println(edges[0].getWeight());
		System.out.println(kruskal.EdgeIsUsable(edges[0]));
		assertTrue(kruskal.isNextEdge(edges[0]));
		kruskal.addEdgeToTree(edges[0]);
		assertFalse(kruskal.EdgeIsUsable(edges[0]));
		assertTrue(kruskal.isNextEdge(edges[2]));
		kruskal.addEdgeToTree(edges[2]);
		System.out.println(kruskal.getTreeProgress());
		assertFalse(kruskal.isNextEdge(edges[3]));
		assertTrue(kruskal.isNextEdge(edges[4]));
		assertFalse(kruskal.completed());
		kruskal.addEdgeToTree(edges[4]);
		assertFalse(kruskal.addEdgeToTree(edges[3]));
		assertFalse(kruskal.addEdgeToTree(edges[1]));
		assertTrue(kruskal.completed());




	}
}