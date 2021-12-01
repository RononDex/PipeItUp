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
		Graph graph = new Graph(incidenceMatrix);
		MinimumSpanningTree prim = new Prim(graph, new Vertex()); // TODO: fix this
		Edge[] edges = graph.getEdges().toArray(new Edge[0]);

		// manual Setup:
		Vertex v1 = new Vertex();
		Vertex v2 = new Vertex();
		Vertex v3 = new Vertex();
		Vertex v4 = new Vertex();
		Edge edge1 = new Edge(1, new Vertex[]{v1, v2});
		Edge edge2 = new Edge(6, new Vertex[]{v1, v3});
		Edge edge3 = new Edge(7, new Vertex[]{v1, v4});
		Edge edge4 = new Edge(4, new Vertex[]{v2, v4});
		Edge edge5 = new Edge(3, new Vertex[]{v3, v4});
//		Arrays.asList(edge1,edge2,edge3,edge4,edge5)
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