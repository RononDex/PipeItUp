package ch.fhnw.ip12.pipeitup.logic;

import java.util.*;
import java.util.stream.Collectors;

/*
minimum spanning tree
 */
public abstract class MinimumSpanningTreeAlgorithm {
	private final Graph graph;

	public MinimumSpanningTreeAlgorithm(Graph graph) {
		this.graph = graph;
	}


	/**
	 * Check if the edge can be used in the process of finding the mst
	 * There may be multiple possible edges to use.
	 *
	 * @param edge to check usability for
	 * @return true if next possible edge contains the given edge
	 */
	abstract boolean isNextEdge(Edge edge);

	public boolean EdgeIsUsable(Edge edge) {
		if (edge.isUsed() || !graph.getEdges().contains(edge)) return false;
		Vertex vertex1 = edge.getVertices()[0];
		Vertex vertex2 = edge.getVertices()[1];
		return !(vertex1.isVisited() && vertex2.isVisited()); // check cycle - return false if both vertices are already visited
	}

	/**
	 * check if all vertices are used
	 *
	 * @return true if all vertices are used, otherwise false
	 */
	public boolean completed() {
		return
				graph.getVertices().stream()
						.allMatch(Vertex::isVisited);
	}

	public int sum() {
		// TODO: return sum of edge weights, only when all vertices are used.
		return graph.getEdges().stream()
				.filter(Edge::isUsed)
				.mapToInt(Edge::getWeight)
				.reduce(0, Integer::sum);
	}

	public boolean addEdgeToTree(Edge edge) {
		if (isNextEdge(edge)) {
			edge.setUsed(true);
			edge.getVertices()[0].setVisited(true);
			edge.getVertices()[1].setVisited(true);
			return true;
		}
		return false;
	}

	public HashSet<Edge> getUnusedEdges() {
		return graph.getEdges().stream()
				.filter(edge -> !edge.isUsed())
				.collect(Collectors.toCollection(HashSet::new));
		// TODO: Override equeals for Hashset to compare object for same weight, start and end vertex.
	}

	public HashSet<Edge> getTreeProgress() {
		return graph.getEdges().stream()
				.filter(Edge::isUsed)
				.collect(Collectors.toCollection(HashSet::new));
	}
}
