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
import ch.fhnw.ip12.pipeitup.logic.utils.GraphLayoutBuilder;

class PrimTest {

	@Test
	void isNextEdge_WithEmptyGraph_ReturnsFalse() {
		PrimAlgorithm testee = new PrimAlgorithm();
		testee.
	}

	@Test
	void isNextEdge() {
		// Arrange
		PrimAlgorithm testee = new PrimAlgorithm();
		GraphLayoutModel graph = createBasicGraphLayout(true);


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

	static GraphLayoutModel createBasicGraphLayout(boolean withFirstVertexBeingStartForPrim) {
		GraphLayoutBuilder builder = new GraphLayoutBuilder()
			.withVertex(0, 1)
			.withVertex(0, 2)
			.withVertex(1, 1)
			.withVertex(1, 2)
			.withEdge(0, 1, 1)
			.withEdge(0, 2, 6)
			.withEdge(0, 3, 7)
			.withEdge(1, 3, 4)
			.withEdge(2, 3, 3);
		
		if (withFirstVertexBeingStartForPrim) {
			builder.withStartVertexForPrim(0);
		}

		return builder.build();
	}
}