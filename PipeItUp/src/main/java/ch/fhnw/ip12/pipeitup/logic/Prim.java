package ch.fhnw.ip12.pipeitup.logic;

import java.util.*;

public class Prim extends MinimumSpanningTree {


	public Prim(int[][] incidenceMatrix) {
		super(incidenceMatrix);
	}

	@Override
	Edge[] nextPossibleEdges() {
		List<Edge> possibleEdges = new ArrayList<>();
		graph.sort(Comparator.comparing(Edge::getWeight));
		for (Edge edge :
				graph) {
			Vertex vertex1 = edge.getVertices()[0];
			Vertex vertex2 = edge.getVertices()[1];
			if (!vertex1.isVisited() && !vertex2.isVisited() // this implies edge.isUsed is also false
					&& !possibleEdges.contains(edge)
					&& edge.getWeight() <= possibleEdges.get(possibleEdges.size()-1).getWeight()){
				// this implies edge.isUsed is also false
				possibleEdges.add(edge);
			}

		}

		// TODO: implement Prim logic
		return new Edge[0];
	}
}
