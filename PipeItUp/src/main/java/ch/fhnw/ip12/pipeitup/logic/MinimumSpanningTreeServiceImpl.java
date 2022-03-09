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
		return nextVertex.getConnectedEdges(graphLayout).stream().filter(e -> e != activeEdge && e.isUsed())
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
		return graphLayout.getVertices().stream().allMatch(
				vertex -> vertex.getConnectedEdges(graphLayout).stream().filter(EdgeModel::isUsed).count() > 1 // isn't isolated
						|| (vertex.getConnectedEdges(graphLayout).stream().filter(EdgeModel::isUsed).count() == 1 // has only one connection
								&& vertex.getConnectedEdges(graphLayout).stream().filter(EdgeModel::isUsed).iterator().next()
										.getConnectedVertices().stream().anyMatch( // and next vertex isn't isolated
												vertex1 -> vertex1.getConnectedEdges(graphLayout).stream().filter(EdgeModel::isUsed)
														.count() > 1)));

	}

	@Override
	public final int usedWeightsSum(GraphLayoutModel graphLayout) {
		return graphLayout.getEdges().stream().filter(EdgeModel::isUsed).mapToInt(EdgeModel::getWeight).reduce(0,
				Integer::sum);
	}

	private static Set<VertexModel> getUsedVertices(GraphLayoutModel graphLayout) {
		return graphLayout.getEdges().stream().filter(edge -> edge.isUsed())
				.flatMap(edge -> edge.getConnectedVertices().stream()).distinct().collect(Collectors.toSet());
	}
}
