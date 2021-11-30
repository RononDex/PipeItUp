package ch.fhnw.ip12.pipeitup.logic;

public class Kruskal extends MinimumSpanningTree {
	public Kruskal(int[][] incidenceMatrix) {
		super(incidenceMatrix);
	}

	@Override
	Edge[] nextPossibleEdges() {
		// TODO: implement Kruskal logic
		return new Edge[0];
	}
}
