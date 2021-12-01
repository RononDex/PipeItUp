package ch.fhnw.ip12.pipeitup.logic;


import java.util.HashSet;

public class Prim extends MinimumSpanningTree {


	public Prim(Graph graph) {
		super(graph);
	}

	@Override
	boolean isNextEdge(Edge edge) {
		if (EdgeInTree(edge)) return false; // check if in tree and not cycle
		// check if it has the lowest available weight
		HashSet<Edge> edges = getUnusedEdges();
//		edges.sort(Comparator.comparing(Edge::getWeight));
		for (Edge edge1 :
				edges) {
			if (EdgeInTree(edge1)) continue;
			if (edge1.getWeight() < edge.getWeight()) return false;
		}
		return true;
	}
}
