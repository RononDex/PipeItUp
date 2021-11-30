package ch.fhnw.ip12.pipeitup.logic;

import org.junit.Test;

/**
* MSTTests
*/
public class MSTTests {

	private class MSTTest extends MST
	{
		public MSTTest(int[][] incidenceMatrix) {
			super(incidenceMatrix);
		}

		@Override
		Edge[] nextPossibleEdges() {
			// TODO Auto-generated method stub
			return new Edge[] { new Edge() };
		}
	}

	@Test
	public void Test()
	{
		MST mst = new MSTTest(new int[1][1]);
		mst.isUsableEdge(new Edge());
		mst.nextPossibleEdges();
		mst.allVerticesUsed();
		mst.sum();

		Edge edge = new Edge();
		edge.isUsed();
		edge.getWeight();
		edge.setUsed(true);
		edge.getVertices();

		Vertex vertex = new Vertex();
		vertex.getIncidentEdges();

		Prim prim = new Prim(new int[1][1]);
		prim.nextPossibleEdges();

		Kruskal kruskal = new Kruskal(new int[1][1]);
		kruskal.nextPossibleEdges();
	}
	
}
