package ch.fhnw.ip12.pipeitup.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;


import org.junit.jupiter.api.Test;

import ch.fhnw.ip12.pipeitup.data.Database;
import ch.fhnw.ip12.pipeitup.data.Models.Vertex;
import ch.fhnw.ip12.pipeitup.data.Models.Edge;
import ch.fhnw.ip12.pipeitup.data.Models.GraphLayout;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;

/**
 * GraphLayoutLoaderImplTests
 */
class GraphLayoutLoaderImplTest {

	@Test
	void getIncidenceMatrixForGraph_WithAnyGraphId_AlwaysReturnsNull() {
		Database databaseMock = mock(Database.class);

		GraphLayoutLoaderImpl testee = new GraphLayoutLoaderImpl(databaseMock);
		int[][] actual = testee.getIncidenceMatrixForGraph(1);

		assertNull(actual);
	}

	@Test
	void getRandomlyWeightedGraph_WithMaxWeightOfZero_ThrowsException() {

		Database databaseMock = mock(Database.class);
		GraphLayoutLoaderImpl testee = new GraphLayoutLoaderImpl(databaseMock);

		try {
			testee.getRandomlyWeightedGraph(0);
			fail();
		} catch (IllegalArgumentException ex) {
			assertEquals(ex.getMessage(), "maxWeight has to be at least 1");
		}
	}

	@Test
	void getRandomlyWeightedGraph_WithMaxWeightNegative_ThrowsException() {

		Database databaseMock = mock(Database.class);
		GraphLayoutLoaderImpl testee = new GraphLayoutLoaderImpl(databaseMock);

		try {
			testee.getRandomlyWeightedGraph(0);
			fail();
		} catch (IllegalArgumentException ex) {
			assertEquals(ex.getMessage(), "maxWeight has to be at least 1");
		}
	}

	@Test
	void getRandomlyWeightedGraph_WithMaxWeightOfOne_AllWeightsAreOne() {

		Database databaseMock = mock(Database.class);
		ArrayList<Vertex> vertices = new ArrayList<>();
		vertices.add(new Vertex(1, 100d, 100d));
		vertices.add(new Vertex(1, 110d, 110d));
		vertices.add(new Vertex(1, 110d, 150d));
		ArrayList<Edge> edges = new ArrayList<>();
		edges.add(new Edge(vertices.get(0), vertices.get(1)));
		edges.add(new Edge(vertices.get(1), vertices.get(2)));
		edges.add(new Edge(vertices.get(2), vertices.get(0)));
		when(databaseMock.getGraphLayout())
				.thenReturn(new GraphLayout(new HashSet<>(vertices), new HashSet<>(edges)));
		GraphLayoutLoaderImpl testee = new GraphLayoutLoaderImpl(databaseMock);

		GraphLayoutModel actual = testee.getRandomlyWeightedGraph(1);

		assertTrue(actual.getEdges().stream().allMatch(x -> x.getWeight() == 1));
	}

	@Test
	void getRandomlyWeightedGraph_WithMaxWeightOfFive_AllWeightsSmallerOrEqualToFive() {

		Database databaseMock = mock(Database.class);
		ArrayList<Vertex> vertices = new ArrayList<>();
		vertices.add(new Vertex(1, 100d, 100d));
		vertices.add(new Vertex(1, 110d, 110d));
		vertices.add(new Vertex(1, 110d, 150d));
		ArrayList<Edge> edges = new ArrayList<>();
		edges.add(new Edge(vertices.get(0), vertices.get(1)));
		edges.add(new Edge(vertices.get(1), vertices.get(2)));
		edges.add(new Edge(vertices.get(2), vertices.get(0)));
		when(databaseMock.getGraphLayout())
				.thenReturn(new GraphLayout(new HashSet<>(vertices), new HashSet<>(edges)));
		GraphLayoutLoaderImpl testee = new GraphLayoutLoaderImpl(databaseMock);

		GraphLayoutModel actual = testee.getRandomlyWeightedGraph(5);

		assertTrue(actual.getEdges().stream().allMatch(x -> x.getWeight() <= 5));
		assertTrue(actual.getEdges().stream().allMatch(x -> x.getWeight() >= 1));
	}
}
