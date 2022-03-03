package ch.fhnw.ip12.pipeitup.logic;

import com.google.inject.Inject;

import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;

final class PrimAlgorithmImpl implements PrimAlgorithm {

	private MinimumSpanningTreeService minimumSpanningTreeService;

	@Inject
	public PrimAlgorithmImpl(MinimumSpanningTreeService minimumSpanningTreeService) {
		this.minimumSpanningTreeService = minimumSpanningTreeService;
	}

	@Override
	public final boolean isNextEdge(GraphLayoutModel graphLayout, EdgeModel edge) {
		if (minimumSpanningTreeService.getUsedEdges(graphLayout).isEmpty()) {
			if (minimumSpanningTreeService.getEdgesConnectedToVertex(graphLayout, graphLayout.getStartVertexForPrim())
					.contains(edge)) {
				int minEdge = minimumSpanningTreeService
						.getEdgesConnectedToVertex(graphLayout, graphLayout.getStartVertexForPrim()).stream()
						.mapToInt(EdgeModel::getWeight).min().orElse(0);
				return minEdge == edge.getWeight();
			}
		}

		int minReachableEdgeWeight = minimumSpanningTreeService.getUsedEdges(graphLayout).stream()
				// get all used vertices
				.flatMap(edge1 -> edge1.getConnectedVertices().stream())
				// get all connectable edges
				.flatMap(vertex -> minimumSpanningTreeService.getEdgesConnectedToVertex(graphLayout, vertex).stream().filter(edge1 -> !edge1.isUsed()))
				// find minimum weight
				.mapToInt(EdgeModel::getWeight).min().orElse(0);

		// check if lowest weight and not in tree and not cycle
		return minReachableEdgeWeight == edge.getWeight() && minimumSpanningTreeService.canEdgeBeUsed(graphLayout, edge);
	}
}
