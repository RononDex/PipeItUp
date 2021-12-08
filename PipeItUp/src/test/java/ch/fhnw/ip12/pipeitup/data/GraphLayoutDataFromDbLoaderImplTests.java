package ch.fhnw.ip12.pipeitup.data;

import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
* GraphLayoutDataFromDbLoaderImplTests
*/
public class GraphLayoutDataFromDbLoaderImplTests {

	@Test
	public void getData_WithNoData_ReturnsNull()
	{
		GraphLayoutDataFromDbLoaderImpl testee = new GraphLayoutDataFromDbLoaderImpl();

		Object[] actual = testee.getData();

		assertNull(actual);
	}
}
