package ch.fhnw.ip12.pipeitup.data;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import ch.fhnw.ip12.pipeitup.data.Models.GraphLayout;

/**
* GraphLayoutDataFromDbLoaderImplTests
*/
class GraphLayoutDataFromDbLoaderImplTests {

	@Test
	void getData_WithNoDatabaseAttached_ReturnsHardcodedGraphLayout()
	{
		GraphLayoutDataFromDbLoaderImpl testee = new GraphLayoutDataFromDbLoaderImpl();

		GraphLayout actual = testee.getGraphLayoutFromDb();

		assertNotNull(actual);
		assertEquals(16, actual.getVertices().size());
		assertEquals(25, actual.getEdges().size());
	}
}
