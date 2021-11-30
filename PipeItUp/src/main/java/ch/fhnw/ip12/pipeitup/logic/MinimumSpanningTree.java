package ch.fhnw.ip12.pipeitup.logic;

import java.util.Arrays;

/*
minimum spanning tree
 */
public abstract class MinimumSpanningTree {

	Edge[] edges;

	public MinimumSpanningTree(int[][] incidenceMatrix){
		// TODO: instantiate vertices and edges
	}

	/**
	 * Check if the edge can be used in the process of finding the mst
	 * @param edge to check usability for
	 * @return true if next possible edge contains the given edge
	 */
	public boolean isUsableEdge(Edge edge){
		return Arrays.asList(nextPossibleEdges()).contains(edge);

	}

	/**
	 * Returns the next possible edge, dependent on the algorithm used.
	 * @return null if no possible edges, otherwise returns all possible edges to use for mst
	 */
	abstract Edge[] nextPossibleEdges();

	/**
	 * check if all vertices are used
	 * @return true if all vertices are used, otherwise false
	 */
	public boolean allVerticesUsed(){
// 		return nextPossibleEdges() == null;
 		return false;
	}

	public int sum(){
		// TODO: return sum of edge weights, only when all vertices are used.
// 		int sum = 0;
// 		for (Edge edge :
// 				edges) {
// 			if (edge.isUsed()){
// 				sum+= edge.getWeight();
// 			}
// 		}
// 			return sum;
 			return 0;
	}

}
