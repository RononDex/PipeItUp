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

	public boolean edgeIsUsable(Edge edge) {
		if (edge.isUsed() || !graph.getEdges().contains(edge)) return false;
		if (edge.getVertices().stream().allMatch(Vertex::isVisited)) { // potential cycle
			edge.setUsed(true); // temporarily set to true to check loop
			boolean loop = createsLoop(new HashSet<>(), edge.getVertices().iterator().next(), edge);
			edge.setUsed(false);
			return !loop;
		}
		return true;
	}

	private static boolean createsLoop(HashSet<Edge> seen, Vertex nextVertex, Edge activeEdge) {
		if (seen.contains(activeEdge)) return true;
		seen.add(activeEdge);
		return nextVertex.getEdges().stream().filter(e -> e != activeEdge && e.isUsed())
				.map(edge -> createsLoop(
						seen,
						edge.getVertices().stream()
								.filter(vertex -> vertex != nextVertex)
								.collect(Collectors.toSet()).iterator().next(),
						edge
				))
				.collect(Collectors.toList())
				.contains(true);
	}

	/**
	 * check if all vertices are used
	 *
	 * @return true if all vertices are used, otherwise false
	 */
	public boolean completed() {
		return graph.getVertices().stream().allMatch(
				vertex -> vertex.getEdges().stream().filter(Edge::isUsed).count() > 1 // isn't isolated
						|| (vertex.getEdges().stream().filter(Edge::isUsed).count() == 1 // has only one connection
						&& vertex.getEdges().stream().filter(Edge::isUsed).iterator().next()
						.getVertices().stream().anyMatch( // and next vertex isn't isolated
								vertex1 -> vertex1.getEdges().stream().filter(Edge::isUsed).count() > 1
						))
		);
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
			edge.getVertices().forEach(vertex -> vertex.setVisited(true));
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

	public HashSet<Edge> getUsedEdges() {
		return graph.getEdges().stream()
				.filter(Edge::isUsed)
				.collect(Collectors.toCollection(HashSet::new));
	}
}
