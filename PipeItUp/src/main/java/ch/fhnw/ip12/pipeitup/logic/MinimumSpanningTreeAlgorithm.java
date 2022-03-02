package ch.fhnw.ip12.pipeitup.logic;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;
import ch.fhnw.ip12.pipeitup.logic.Models.VertexModel;

/*
minimum spanning tree
 */
public abstract class MinimumSpanningTreeAlgorithm {

	/**
	 * Check if the edge can be used in the process of finding the mst There may be
	 * multiple possible edges to use.
	 *
	 * @param edge to check usability for
	 * @return true if next possible edge contains the given edge
	 */
	abstract boolean isNextEdge(GraphLayoutModel graphLayout, EdgeModel edge);

	public boolean edgeIsUsable(GraphLayoutModel graphLayout, EdgeModel edge) {
		List<VertexModel> usedVertices = getUsedVertices(graphLayout);
		if (edge.isUsed())
			return false;
		if (edge.getConnectedVertices().stream().allMatch(vertex -> usedVertices.contains(vertex))) { // potential cycle
			edge.setUsed(true); // temporarily set to true to check loop
			boolean isLoop = createsLoop(graphLayout, new HashSet<>(), edge.getVertex1(), edge);
			edge.setUsed(false);
			return !isLoop;
		}
		return true;
	}

	private boolean createsLoop(GraphLayoutModel graphLayout, HashSet<EdgeModel> seen, VertexModel nextVertex,
			EdgeModel activeEdge) {
		if (seen.contains(activeEdge))
			return true;
		seen.add(activeEdge);
		return getEdgesForVertex(graphLayout, nextVertex).stream().filter(e -> e != activeEdge && e.isUsed())
				.map(edge -> createsLoop(graphLayout, seen,
						edge.getConnectedVertices().stream().filter(vertex -> vertex != nextVertex)
								.collect(Collectors.toSet()).iterator().next(),
						edge))
				.collect(Collectors.toList()).contains(true);
	}

	/**
	 * check if all vertices are used
	 *
	 * @return true if all vertices are used, otherwise false
	 */
	public boolean completed(GraphLayoutModel graphLayout) {
		List<VertexModel> usedVertices = getUsedVertices(graphLayout);
		return usedVertices.size() == graphLayout.getVertices().size();
	}

	public int sum(GraphLayoutModel graphLayout) {
		// TODO: return sum of edge weights, only when all vertices are used.
		return graphLayout.getEdges().stream().filter(EdgeModel::isUsed).mapToInt(EdgeModel::getWeight).reduce(0,
				Integer::sum);
	}

	protected boolean addEdgeToTree(GraphLayoutModel graphLayout, EdgeModel edge) {
		if (isNextEdge(graphLayout, edge)) {
			edge.setUsed(true);
			return true;
		}
		return false;
	}

	public HashSet<EdgeModel> getUnusedEdges(GraphLayoutModel graphLayout) {
		return graphLayout.getEdges().stream().filter(edge -> !edge.isUsed())
				.collect(Collectors.toCollection(HashSet::new));
		// TODO: Override equeals for Hashset to compare object for same weight, start
		// and end vertex.
	}

	public HashSet<EdgeModel> getUsedEdges(GraphLayoutModel graphLayout) {
		return graphLayout.getEdges().stream().filter(EdgeModel::isUsed).collect(Collectors.toCollection(HashSet::new));
	}

	private static List<VertexModel> getUsedVertices(GraphLayoutModel graphLayout) {
		return graphLayout.getEdges().stream().filter(edge -> edge.isUsed())
				.flatMap(edge -> edge.getConnectedVertices().stream()).distinct().collect(Collectors.toList());
	}

	protected static List<EdgeModel> getEdgesForVertex(GraphLayoutModel graphLayout, VertexModel vertex)
	{
		return graphLayout.getEdges().stream()
			.filter(x -> x.getVertex1() == vertex || x.getVertex2() == vertex)
			.collect(Collectors.toList());
	}
}
