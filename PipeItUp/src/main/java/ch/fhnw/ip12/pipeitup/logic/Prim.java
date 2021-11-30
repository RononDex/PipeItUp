package ch.fhnw.ip12.pipeitup.logic;

public class Prim extends MinimumSpanningTreeAlgorithm {
	private final Vertex start;

	public Prim(Graph graph, Vertex start) {
		super(graph);
		this.start = start;
	}

	@Override
	boolean isNextEdge(Edge edge) {
		if (getUsedEdges().isEmpty()){
			if (start.getEdges().contains(edge)){
				int minEdge = start.getEdges().stream()
						.mapToInt(Edge::getWeight)
						.min().orElse(0);
				return minEdge == edge.getWeight();
			}
		}
		int minEdge = getUsedEdges().stream()
				.flatMap(edge1 -> edge1.getVertices().stream()) // get all used vertices
				.flatMap(vertex -> vertex.getEdges().stream()
						.filter(edge1 -> !edge1.isUsed())) // get all connectable edges
				.mapToInt(Edge::getWeight)
				.min().orElse(0);

		// check if lowest weight and not in tree and not cycle
		return minEdge == edge.getWeight() && edgeIsUsable(edge);
	}
}
