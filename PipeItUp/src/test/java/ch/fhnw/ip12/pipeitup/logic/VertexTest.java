package ch.fhnw.ip12.pipeitup.logic;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class VertexTest {
	Vertex v1 = new Vertex();
	Vertex v2 = new Vertex();
	Edge e1 = new Edge(5, new HashSet<>() {{
		add(v1);
		add(v2);
	}});
	@Test
	void addEdge() {
		assertTrue(v1.addEdge(e1));
		assertFalse(v1.addEdge(e1));
	}
}