package ch.fhnw.ip12.pipeitup.logic;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;

@ExcludeTypeFromJacocoGeneratedReport
public class Prim extends MinimumSpanningTreeAlgorithm {
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
