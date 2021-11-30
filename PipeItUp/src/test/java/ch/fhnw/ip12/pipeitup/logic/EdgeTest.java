package ch.fhnw.ip12.pipeitup.logic;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {
	Vertex v1 = new Vertex();
	Vertex v2 = new Vertex();
	Edge e1 = new Edge(5, new HashSet<>() {{
		add(v1);
		add(v2);
	}});


	@Test
	void getWeight() {
		assertEquals(5, e1.getWeight());
	}

	@Test
	void getVertices() {
		assertEquals(new HashSet<>() {{
			add(v1);
			add(v2);
		}}, e1.getVertices());
	}

	@Test
	void isUsedSetUsed() {
		e1.setUsed(true);
		assertTrue(e1.isUsed());
		e1.setUsed(false);
		assertFalse(e1.isUsed());
	}
}