package ch.fhnw.ip12.pipeitup.logic;

import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;
import ch.fhnw.ip12.pipeitup.logic.Models.VertexModel;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
minimum spanning tree
 */
public class MinimumSpanningTreeServiceImpl implements MinimumSpanningTreeService {

	@Override
	public final boolean canEdgeBeUsed(GraphLayoutModel graphLayout, EdgeModel edge) {
		if (edge.isUsed()) {
			return false;
		}

		Set<VertexModel> usedVertices = getUsedVertices(graphLayout);
		boolean isLoop = false;
		if (edge.getConnectedVertices().stream().allMatch(vertex -> usedVertices.contains(vertex))) { // potential cycle
			edge.setUsed(true); // temporarily set to true to check loop
			isLoop = createsLoop(graphLayout, new HashSet<>(), edge.getVertex1(), edge);
			edge.setUsed(false);
		}
		return !isLoop;
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
	 * Checks if the spanning tree is complete (has n - 1 edges active)
	 * 
	 * @return true if all n-1 edges are used (n = number of vertices)
	 */
	@Override
	public final boolean isMspCompleted(GraphLayoutModel graphLayout) {
		return graphLayout.getEdges().stream().filter(e -> e.isUsed()).count() == graphLayout.getVertices().size() - 1;

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
