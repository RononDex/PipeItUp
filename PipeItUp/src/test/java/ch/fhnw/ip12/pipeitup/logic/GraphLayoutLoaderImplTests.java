package ch.fhnw.ip12.pipeitup.logic;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import ch.fhnw.ip12.pipeitup.data.GraphLayoutDataLoader;

/**
 * GraphLayoutLoaderImplTests
 */
public class GraphLayoutLoaderImplTests {

	@Test
	public void Test() {
		GraphLayoutDataLoader graphLayoutDataLoaderMock = mock(GraphLayoutDataLoader.class);
		when(graphLayoutDataLoaderMock.getData()).thenReturn(
				new int[][]{
				new int[]{1,2,3},
				new int[]{1,2,3},
				new int[]{1,2,3}
		});

		GraphLayoutLoaderImpl testee = new GraphLayoutLoaderImpl(graphLayoutDataLoaderMock);
		Graph actual = testee.getGraph(1);

		assertNull(actual);
	}
}
