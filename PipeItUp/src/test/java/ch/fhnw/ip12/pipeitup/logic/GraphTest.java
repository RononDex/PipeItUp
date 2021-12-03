package ch.fhnw.ip12.pipeitup.logic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
		Vertex v1 = new Vertex();
		Vertex v2 = new Vertex();
		Vertex v3 = new Vertex();
		Vertex v4 = new Vertex();
		Edge e1 = new Edge(1, new HashSet<>(){{add(v1);add(v2);}});
		Edge e2 = new Edge(6, new HashSet<>(){{add(v1);add(v3);}});
		Edge e3 = new Edge(7, new HashSet<>(){{add(v1);add(v4);}});
		Edge e4 = new Edge(4, new HashSet<>(){{add(v2);add(v4);}});
		Edge e5 = new Edge(3, new HashSet<>(){{add(v3);add(v4);}});
		Graph graph = new Graph(new LinkedHashSet<>(Arrays.asList(e1,e2,e3,e4,e5)));



	@Test
	void fromIncidentMatrix() {
		// useCase1:
		int[][] incidenceMatrix = {
				{1, 6, 1, 0, 0},
				{1, 0, 0, 4, 0},
				{0, 6, 0, 0, 3},
				{0, 0, 1, 4, 3}
		};
		Graph graphIM = Graph.fromIncidentMatrix(incidenceMatrix);

		v1.addEdge(e1);
		v2.addEdge(e1);
		v1.addEdge(e2);
		v3.addEdge(e2);
		v1.addEdge(e3);
		v4.addEdge(e3);
		v2.addEdge(e4);
		v4.addEdge(e4);
		v3.addEdge(e5);
		v4.addEdge(e5);



		// assertEquals(graph, graphIM); // TODO: Graph equals needs overwriting, but is recursive...
	}

	@Test
	void getEdges() {
		assertEquals(new LinkedHashSet<>(Arrays.asList(e1,e2, e3, e4, e5)),graph.getEdges());

	}

	@Test
	void getVertices() {
		assertEquals(new HashSet<>(Arrays.asList(v1,v2,v3,v4)), graph.getVertices());
	}
}