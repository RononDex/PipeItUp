package ch.fhnw.ip12.pipeitup.logic;

import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;
import com.google.inject.Inject;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

final class KruskalAlgorithmImpl implements KruskalAlgorithm {

	private MinimumSpanningTreeService minimumSpanningTreeService;

	@Inject
	public KruskalAlgorithmImpl(MinimumSpanningTreeService minimumSpanningTreeService) {
		this.minimumSpanningTreeService = minimumSpanningTreeService;
	}

	@Override
	public boolean isEdgeValidPick(GraphLayoutModel graphLayout, EdgeModel edge) {
		if (!minimumSpanningTreeService.canEdgeBeUsed(graphLayout, edge)
				|| minimumSpanningTreeService.isMspCompleted(graphLayout))
			return false; // check if in tree and not cycle

		// check if it has the lowest available weight
		Set<EdgeModel> edges = getUnusedEdges(graphLayout);
		for (EdgeModel edge1 : edges) {
			if (!minimumSpanningTreeService.canEdgeBeUsed(graphLayout, edge1))
				continue;
			if (edge1.getWeight() < edge.getWeight())
				return false;
		}
		return true;
	}

	private static final Set<EdgeModel> getUnusedEdges(GraphLayoutModel graphLayout) {
		return graphLayout.getEdges().stream().filter(edge -> !edge.isUsed())
				.collect(Collectors.toCollection(HashSet::new));
	}
}
