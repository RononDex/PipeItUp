package ch.fhnw.ip12.pipeitup.logic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MinimumSpanningTreeAlgorithmTest {
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
	MinimumSpanningTreeAlgorithm mstAlg = new MinimumSpanningTreeAlgorithm(graph) {
		@Override
		boolean isNextEdge(Edge edge) {
			return false;
		}
	};

	@Test
	void edgeIsUsable() {

		// check single factors.
		e1.setUsed(false);
		assertTrue(mstAlg.edgeIsUsable(e1));
		e1.setUsed(true);
		assertFalse(mstAlg.edgeIsUsable(e1));
		Edge eNotInGraph = new Edge(55, new HashSet<>(){{add(v1);add(v2);}});
		assertFalse(mstAlg.edgeIsUsable(eNotInGraph));

		// check if multiple things aren't alright.
		eNotInGraph.setUsed(true);
		assertFalse(mstAlg.edgeIsUsable(eNotInGraph));

		// build a cycle
		v1.setVisited(true);
		v2.setVisited(true);
		assertFalse(mstAlg.edgeIsUsable(e1));
		assertFalse(mstAlg.edgeIsUsable(eNotInGraph));

		// no cycle and not used and in graph
		v2.setVisited(false);
		e1.setUsed(false);
		assertTrue(mstAlg.edgeIsUsable(e1));
		eNotInGraph.setUsed(false);
		assertFalse(mstAlg.edgeIsUsable(eNotInGraph));
	}

	@Test
	void completed() {
		v1.setVisited(true);
		v2.setVisited(true);
		v3.setVisited(true);
		v4.setVisited(true);
		assertFalse(mstAlg.completed());
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
		e1.setUsed(true);
		e2.setUsed(false);
		e3.setUsed(false);
		e4.setUsed(true);
		e5.setUsed(true);
		assertTrue(mstAlg.completed());
	}

	@Test
	void sum() {
		e1.setUsed(true);
		e2.setUsed(true);
		e3.setUsed(false);
		e4.setUsed(false);
		e5.setUsed(true);
		int sum = e1.getWeight() + e2.getWeight() + e5.getWeight();
		assertEquals(sum, mstAlg.sum());
	}



	@Test
	void getUnusedEdges() {
		e1.setUsed(false);
		e2.setUsed(false);
		e3.setUsed(false);
		e4.setUsed(false);
		e5.setUsed(false);
		assertEquals(new HashSet<>(Arrays.asList(e1,e2,e3,e4,e5)), mstAlg.getUnusedEdges());
		e1.setUsed(true);
		assertEquals(new HashSet<>(Arrays.asList(e2,e3,e4,e5)), mstAlg.getUnusedEdges());
		e5.setUsed(true);
		assertEquals(new HashSet<>(Arrays.asList(e2,e3,e4)), mstAlg.getUnusedEdges());
		e2.setUsed(true);
		e3.setUsed(true);
		e4.setUsed(true);
		e5.setUsed(true);
		assertEquals(new HashSet<>(), mstAlg.getUnusedEdges());
	}

	@Test
	void getUsedEdges() {
		e1.setUsed(false);
		e2.setUsed(false);
		e3.setUsed(false);
		e4.setUsed(false);
		e5.setUsed(false);
		assertEquals(new HashSet<>(), mstAlg.getUsedEdges());
		e1.setUsed(true);
		assertEquals(new HashSet<>(List.of(e1)), mstAlg.getUsedEdges());
		e5.setUsed(true);
		assertEquals(new HashSet<>(Arrays.asList(e1,e5)), mstAlg.getUsedEdges());
		e2.setUsed(true);
		e3.setUsed(true);
		e4.setUsed(true);
		e5.setUsed(true);
		assertEquals(new HashSet<>(Arrays.asList(e1,e2,e3,e4,e5)), mstAlg.getUsedEdges());

	}
}