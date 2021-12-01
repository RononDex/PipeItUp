package ch.fhnw.ip12.pipeitup.logic;


public class Prim extends MinimumSpanningTree {
	private final Vertex start;

	public Prim(Graph graph, Vertex start) {
		super(graph);
		this.start = start;
	}

	@Override
	boolean isNextEdge(Edge edge) { // TODO: add prim logic
		return false;
	}
}
