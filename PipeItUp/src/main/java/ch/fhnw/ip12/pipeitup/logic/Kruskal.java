package ch.fhnw.ip12.pipeitup.logic;

import java.util.HashSet;

import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;

public class Kruskal extends MinimumSpanningTreeAlgorithm {

	@Override
	boolean isNextEdge(GraphLayoutModel graphLayout, EdgeModel edge) {
		if (!edgeIsUsable(graphLayout, edge)) return false; // check if in tree and not cycle
		// check if it has the lowest available weight
		HashSet<EdgeModel> edges = getUnusedEdges(graphLayout);
		for (EdgeModel edge1 :
				edges) {
			if (!edgeIsUsable(graphLayout, edge1)) continue;
			if (edge1.getWeight() < edge.getWeight()) return false;
		}
		return true;
	}
}
