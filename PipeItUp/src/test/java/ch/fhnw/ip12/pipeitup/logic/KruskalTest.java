package ch.fhnw.ip12.pipeitup.logic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

class KruskalTest {

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
	Kruskal kruskal = new Kruskal(graph);

	@Test
	void isNextEdge() {

		assertTrue(kruskal.isNextEdge(e1));
		assertFalse(kruskal.isNextEdge(e2));
		assertFalse(kruskal.isNextEdge(e3));
		assertFalse(kruskal.isNextEdge(e4));
		assertFalse(kruskal.isNextEdge(e5));
		e1.setUsed(true);
		e2.setUsed(true);
		assertFalse(kruskal.isNextEdge(e1));
		assertFalse(kruskal.isNextEdge(e3));
		assertTrue(kruskal.isNextEdge(e5));
		e3.setUsed(true);
		e4.setUsed(true);
		e5.setUsed(true);
		assertFalse(kruskal.isNextEdge(e1));
		assertFalse(kruskal.isNextEdge(e2));
		assertFalse(kruskal.isNextEdge(e3));
		assertFalse(kruskal.isNextEdge(e4));
		assertFalse(kruskal.isNextEdge(e5));
	}

	@Test
	void addEdgeToTree() {
		e1.setUsed(true);
		v1.setVisited(true);
		v2.setVisited(true);

		e2.setUsed(false);
		e3.setUsed(false);
		e4.setUsed(false);
		e5.setUsed(false);
		v3.setVisited(false);
		v4.setVisited(false);

		assertFalse(kruskal.addEdgeToTree(e1)); // can't use edge again
		assertFalse(kruskal.addEdgeToTree(e4)); // is not the lowest edge

		assertTrue(kruskal.addEdgeToTree(e5)); // is next lowest edge
		assertTrue(v3.isVisited()); // adding e5 should have set its vertices to visited.
		assertTrue(v4.isVisited());

	}
}