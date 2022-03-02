package ch.fhnw.ip12.pipeitup.logic;

import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;
import ch.fhnw.ip12.pipeitup.logic.Models.VertexModel;

public class Prim extends MinimumSpanningTreeAlgorithm {
	private VertexModel start;

	public void setStartVertex(VertexModel start) {
		this.start = start;
	}

	@Override
	boolean isNextEdge(GraphLayoutModel graphLayout, EdgeModel edge) {
		if (getUsedEdges(graphLayout).isEmpty()) {
			if (getEdgesForVertex(graphLayout, start).contains(edge)) {
				int minEdge = getEdgesForVertex(graphLayout, start).stream().mapToInt(EdgeModel::getWeight).min()
						.orElse(0);
				return minEdge == edge.getWeight();
			}
		}
		int minEdge = getUsedEdges(graphLayout).stream().flatMap(edge1 -> edge1.getConnectedVertices().stream()) // get all used
																										// vertices
				.flatMap(vertex -> getEdgesForVertex(graphLayout, vertex).stream().filter(edge1 -> !edge1.isUsed())) // get all connectable
																								// edges
				.mapToInt(EdgeModel::getWeight).min().orElse(0);

		// check if lowest weight and not in tree and not cycle
		return minEdge == edge.getWeight() && edgeIsUsable(graphLayout, edge);
	}
}
