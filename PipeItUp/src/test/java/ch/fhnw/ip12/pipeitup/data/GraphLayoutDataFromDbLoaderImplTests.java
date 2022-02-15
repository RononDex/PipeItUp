package ch.fhnw.ip12.pipeitup.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import ch.fhnw.ip12.pipeitup.data.Models.GraphLayout;
import ch.fhnw.ip12.pipeitup.data.Models.Vertex;

/**
* GraphLayoutDataFromDbLoaderImplTests
*/
public class GraphLayoutDataFromDbLoaderImplTests {

	@Test
	public void getData_WithNoDatabaseAttached_ReturnsHardcodedGraphLayout()
	{
		GraphLayoutDataFromDbLoaderImpl testee = new GraphLayoutDataFromDbLoaderImpl();

		GraphLayout actual = testee.getGraphLayoutFromDb();

		assertNotNull(actual);
		assertEquals(16, actual.getVertices().size());
		assertEquals(25, actual.getEdges().size());
	}
}
