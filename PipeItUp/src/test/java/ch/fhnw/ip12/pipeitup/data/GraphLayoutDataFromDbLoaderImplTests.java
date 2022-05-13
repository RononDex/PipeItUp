package ch.fhnw.ip12.pipeitup.data;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import ch.fhnw.ip12.pipeitup.data.Models.GraphLayout;

import java.util.ArrayList;

/**
 * GraphLayoutDataFromDbLoaderImplTests
 */
class GraphLayoutDataFromDbLoaderImplTests {
	@Test
	void getData_WithNoDatabaseAttached_ReturnsHardcodedGraphLayout() {

		Database db = new DatabaseImpl();
		GraphLayout actual = db.getGraphLayout();

		assertNotNull(actual);
		assertEquals(16, actual.getVertices().size());
		assertEquals(26, actual.getEdges().size());
	}
}
