package main.java.ch.fhnw.ip12.pipeitup.app.logic;

import java.util.Arrays;

/*
minimum spanning tree
 */
public abstract class MST {

	Edge[] edges;

	public MST (int[][] incidentMatrix){
		// TODO: instantiate vertices and edges
	}

	public boolean isUsableEdge(Edge edge){
		return Arrays.asList(nextPossibleEdges()).contains(edge);

	}

	abstract Edge[] nextPossibleEdges();



	public int sum(){
		// TODO: return sum of edge weights, only when all vertices are used.
		return 0;
	}

}
