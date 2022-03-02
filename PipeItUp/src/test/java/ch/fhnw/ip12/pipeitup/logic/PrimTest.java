package ch.fhnw.ip12.pipeitup.logic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

import org.junit.jupiter.api.Test;

import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;
import ch.fhnw.ip12.pipeitup.logic.Models.VertexModel;

class PrimTest {

	VertexModel v1 = new VertexModel(0, 1);
	VertexModel v2 = new VertexModel(0, 2);
	VertexModel v3 = new VertexModel(0, 3);
	VertexModel v4 = new VertexModel(0, 4);
	EdgeModel e1 = new EdgeModel(v1, v2, 1);
	EdgeModel e2 = new EdgeModel(v1, v3, 6);
	EdgeModel e3 = new EdgeModel(v1, v4, 7);
	EdgeModel e4 = new EdgeModel(v2, v4, 4);
	EdgeModel e5 = new EdgeModel(v3, v4, 3);
	GraphLayoutModel graph = new GraphLayoutModel(Arrays.asList(v1, v2, v3, v4), Arrays.asList(e1, e2, e3, e4, e5));

	@Test
	void isNextEdge() {
		Prim testee = new Prim();
		testee.setStartVertex(start);

		assertTrue(testee.isNextEdge(e1));
		assertFalse(testee.isNextEdge(e2));
		assertFalse(testee.isNextEdge(e3));
		assertFalse(testee.isNextEdge(e4));
		assertFalse(testee.isNextEdge(e5));
		e1.setUsed(true);
		v1.setVisited(true);
		v2.setVisited(true);
		assertFalse(testee.isNextEdge(e1));
		assertFalse(testee.isNextEdge(e2));
		assertFalse(testee.isNextEdge(e3));
		assertTrue(testee.isNextEdge(e4));
		assertFalse(testee.isNextEdge(e5));
		e4.setUsed(true);
		v4.setVisited(true);
		e5.setUsed(true);
		v3.setVisited(true);
		assertFalse(testee.isNextEdge(e1));
		assertFalse(testee.isNextEdge(e2));
		assertFalse(testee.isNextEdge(e3));
		assertFalse(testee.isNextEdge(e4));
		assertFalse(testee.isNextEdge(e5));
	}
}