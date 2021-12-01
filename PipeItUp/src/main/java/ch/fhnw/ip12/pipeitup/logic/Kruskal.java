package ch.fhnw.ip12.pipeitup.logic;

import java.util.List;

public class Kruskal extends MinimumSpanningTree {


	public Kruskal(Graph graph) {
		super(graph);
	}

	@Override
	boolean isNextEdge(Edge edge) {
		return false;
	}
}
