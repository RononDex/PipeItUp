package ch.fhnw.ip12.pipeitup.logic;

import java.util.HashSet;

public class Kruskal extends MinimumSpanningTreeAlgorithm {


	public Kruskal(Graph graph) {
		super(graph);
	}

	@Override
	boolean isNextEdge(Edge edge) {
		if (!EdgeIsUsable(edge)) return false; // check if in tree and not cycle
		// check if it has the lowest available weight
		HashSet<Edge> edges = getUnusedEdges();
		for (Edge edge1 :
				edges) {
			if (!EdgeIsUsable(edge1)) continue;
			if (edge1.getWeight() < edge.getWeight()) return false;
		}
		return true;
	}
}
