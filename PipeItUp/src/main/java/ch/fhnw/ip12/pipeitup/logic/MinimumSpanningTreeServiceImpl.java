package ch.fhnw.ip12.pipeitup.logic;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;
import ch.fhnw.ip12.pipeitup.logic.Models.VertexModel;

/*
minimum spanning tree
 */
public class MinimumSpanningTreeServiceImpl implements MinimumSpanningTreeService {

	@Override
	public final boolean canEdgeBeUsed(GraphLayoutModel graphLayout, EdgeModel edge) {
		Set<VertexModel> usedVertices = getUsedVertices(graphLayout);
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

	private final boolean createsLoop(GraphLayoutModel graphLayout, HashSet<EdgeModel> seen, VertexModel nextVertex,
			EdgeModel activeEdge) {
		if (seen.contains(activeEdge))
			return true;
		seen.add(activeEdge);
		return getEdgesConnectedToVertex(graphLayout, nextVertex).stream().filter(e -> e != activeEdge && e.isUsed())
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
	@Override
	public final boolean isMspCompleted(GraphLayoutModel graphLayout) {
		Set<VertexModel> usedVertices = getUsedVertices(graphLayout);
		return usedVertices.size() == graphLayout.getVertices().size();
	}

	@Override
	public final int usedWeightsSum(GraphLayoutModel graphLayout) {
		return graphLayout.getEdges().stream().filter(EdgeModel::isUsed).mapToInt(EdgeModel::getWeight).reduce(0,
				Integer::sum);
	}

	@Override
	public final Set<EdgeModel> getUnusedEdges(GraphLayoutModel graphLayout) {
		return graphLayout.getEdges().stream().filter(edge -> !edge.isUsed())
				.collect(Collectors.toCollection(HashSet::new));
		// TODO: Override equeals for Hashset to compare object for same weight, start
		// and end vertex.
	}

	@Override
	public final Set<EdgeModel> getUsedEdges(GraphLayoutModel graphLayout) {
		return graphLayout.getEdges().stream().filter(EdgeModel::isUsed).collect(Collectors.toCollection(HashSet::new));
	}

	private static Set<VertexModel> getUsedVertices(GraphLayoutModel graphLayout) {
		return graphLayout.getEdges().stream().filter(edge -> edge.isUsed())
				.flatMap(edge -> edge.getConnectedVertices().stream()).distinct().collect(Collectors.toSet());
	}

	@Override
	public final Set<EdgeModel> getEdgesConnectedToVertex(GraphLayoutModel graphLayout, VertexModel vertex) {
		return graphLayout.getEdges().stream().filter(x -> x.getVertex1() == vertex || x.getVertex2() == vertex)
				.collect(Collectors.toSet());
	}
}
