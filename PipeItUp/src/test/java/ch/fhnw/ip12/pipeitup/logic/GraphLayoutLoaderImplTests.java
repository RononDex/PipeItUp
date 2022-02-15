package ch.fhnw.ip12.pipeitup.logic;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ch.fhnw.ip12.pipeitup.data.GraphLayoutDataLoader;
import ch.fhnw.ip12.pipeitup.data.Models.Vertex;
import ch.fhnw.ip12.pipeitup.data.Models.Edge;
import ch.fhnw.ip12.pipeitup.data.Models.GraphLayout;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;

/**
 * GraphLayoutLoaderImplTests
 */
public class GraphLayoutLoaderImplTests {

	@Test
	public void getIncidenceMatrixForGraph_WithAnyGraphId_AlwaysReturnsNull() {
		GraphLayoutDataLoader graphLayoutDataLoaderMock = mock(GraphLayoutDataLoader.class);

		GraphLayoutLoaderImpl testee = new GraphLayoutLoaderImpl(graphLayoutDataLoaderMock);
		int[][] actual = testee.getIncidenceMatrixForGraph(1);

		assertNull(actual);
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void getRandomlyWeightedGraph_WithMaxWeightOfZero_ThrowsException() {

		GraphLayoutDataLoader graphLayoutDataLoaderMock = mock(GraphLayoutDataLoader.class);
		GraphLayoutLoaderImpl testee = new GraphLayoutLoaderImpl(graphLayoutDataLoaderMock);

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("maxWeight has to be at least 1");

		GraphLayoutModel actual = testee.getRandomlyWeightedGraph(0);

		expectedException = ExpectedException.none();
	}

	@Test
	public void getRandomlyWeightedGraph_WithMaxWeightNegative_ThrowsException() {

		GraphLayoutDataLoader graphLayoutDataLoaderMock = mock(GraphLayoutDataLoader.class);
		GraphLayoutLoaderImpl testee = new GraphLayoutLoaderImpl(graphLayoutDataLoaderMock);

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("maxWeight has to be at least 1");

		GraphLayoutModel actual = testee.getRandomlyWeightedGraph(-10);

		expectedException = ExpectedException.none();
	}

	@Test
	public void getRandomlyWeightedGraph_WithMaxWeightOfOne_AllWeightsAreOne() {

		GraphLayoutDataLoader graphLayoutDataLoaderMock = mock(GraphLayoutDataLoader.class);
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		vertices.add(new Vertex(1, 100d, 100d));
		vertices.add(new Vertex(1, 110d, 110d));
		vertices.add(new Vertex(1, 110d, 150d));
		ArrayList<Edge> edges = new ArrayList<Edge>();
		edges.add(new Edge(vertices.get(0), vertices.get(1)));
		edges.add(new Edge(vertices.get(1), vertices.get(2)));
		edges.add(new Edge(vertices.get(2), vertices.get(0)));
		when(graphLayoutDataLoaderMock.getGraphLayoutFromDb()).thenReturn(new GraphLayout(vertices, edges));
		GraphLayoutLoaderImpl testee = new GraphLayoutLoaderImpl(graphLayoutDataLoaderMock);

		GraphLayoutModel actual = testee.getRandomlyWeightedGraph(1);

		assertTrue(actual.getEdges().stream().allMatch(x -> x.getWeight() == 1));
	}

	@Test
	public void getRandomlyWeightedGraph_WithMaxWeightOfFive_AllWeightsSmallerOrEqualToFive() {

		GraphLayoutDataLoader graphLayoutDataLoaderMock = mock(GraphLayoutDataLoader.class);
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		vertices.add(new Vertex(1, 100d, 100d));
		vertices.add(new Vertex(1, 110d, 110d));
		vertices.add(new Vertex(1, 110d, 150d));
		ArrayList<Edge> edges = new ArrayList<Edge>();
		edges.add(new Edge(vertices.get(0), vertices.get(1)));
		edges.add(new Edge(vertices.get(1), vertices.get(2)));
		edges.add(new Edge(vertices.get(2), vertices.get(0)));
		when(graphLayoutDataLoaderMock.getGraphLayoutFromDb()).thenReturn(new GraphLayout(vertices, edges));
		GraphLayoutLoaderImpl testee = new GraphLayoutLoaderImpl(graphLayoutDataLoaderMock);

		GraphLayoutModel actual = testee.getRandomlyWeightedGraph(5);

		assertTrue(actual.getEdges().stream().allMatch(x -> x.getWeight() <= 5));
		assertTrue(actual.getEdges().stream().allMatch(x -> x.getWeight() >= 1));
	}
}
