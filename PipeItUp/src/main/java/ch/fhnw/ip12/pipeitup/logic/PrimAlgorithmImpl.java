package ch.fhnw.ip12.pipeitup.logic;

import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;
import com.google.inject.Inject;

import java.util.Set;
import java.util.stream.Collectors;

final class PrimAlgorithmImpl implements PrimAlgorithm {

	private MinimumSpanningTreeService minimumSpanningTreeService;

	@Inject
	public PrimAlgorithmImpl(MinimumSpanningTreeService minimumSpanningTreeService) {
		this.minimumSpanningTreeService = minimumSpanningTreeService;
	}

	@Override
	public final boolean isEdgeValidPick(GraphLayoutModel graphLayout, EdgeModel edge) {

		if (graphLayout.getStartVertexForPrim() == null || minimumSpanningTreeService.isMspCompleted(graphLayout))
		{
			return false;
		}

		if (getUsedEdges(graphLayout).isEmpty()) {
			Set<EdgeModel> connectedEdgesToStartVertex = graphLayout.getStartVertexForPrim().getConnectedEdges(graphLayout);
			if (connectedEdgesToStartVertex.contains(edge)) {
				int minEdge = connectedEdgesToStartVertex.stream().mapToInt(EdgeModel::getWeight).min().orElse(0);
				return minEdge == edge.getWeight();
			}
		}

		Set<EdgeModel> reachableEdges = getUsedEdges(graphLayout).stream()
				.flatMap(edge1 -> edge1.getConnectedVertices().stream())
				.flatMap(vertex -> vertex.getConnectedEdges(graphLayout).stream())
				.filter(edge1 -> minimumSpanningTreeService.canEdgeBeUsed(graphLayout, edge1))
				.collect(Collectors.toSet());

		int minReachableEdgeWeight = reachableEdges.stream()
				.mapToInt(EdgeModel::getWeight).min().orElse(0);

		// check if lowest weight and not in tree and not cycle
		return minReachableEdgeWeight == edge.getWeight()
				&& reachableEdges.contains(edge);
	}

	private static final Set<EdgeModel> getUsedEdges(GraphLayoutModel graphLayout) {
		return graphLayout.getEdges().stream().filter(EdgeModel::isUsed).collect(Collectors.toSet());
	}
}
