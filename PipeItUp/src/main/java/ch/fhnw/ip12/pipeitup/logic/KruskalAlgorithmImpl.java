package ch.fhnw.ip12.pipeitup.logic;

import java.util.Set;

import com.google.inject.Inject;

import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;

final class KruskalAlgorithmImpl implements KruskalAlgorithm {

	private MinimumSpanningTreeService minimumSpanningTreeService;

	@Inject
	public KruskalAlgorithmImpl(MinimumSpanningTreeService minimumSpanningTreeService) {
		this.minimumSpanningTreeService = minimumSpanningTreeService;
	}

	@Override
	public boolean isNextEdge(GraphLayoutModel graphLayout, EdgeModel edge) {
		if (!minimumSpanningTreeService.canEdgeBeUsed(graphLayout, edge)) return false; // check if in tree and not cycle
		// check if it has the lowest available weight
		Set<EdgeModel> edges = minimumSpanningTreeService.getUnusedEdges(graphLayout);
		for (EdgeModel edge1 :
				edges) {
			if (!minimumSpanningTreeService.canEdgeBeUsed(graphLayout, edge1)) continue;
			if (edge1.getWeight() < edge.getWeight()) return false;
		}
		return true;
	}
}
