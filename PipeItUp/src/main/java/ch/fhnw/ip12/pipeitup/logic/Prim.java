package ch.fhnw.ip12.pipeitup.logic;

public class Prim extends MST{


	public Prim(int[][] incidenceMatrix) {
		super(incidenceMatrix);
	}

	@Override
	Edge[] nextPossibleEdges() {
		// TODO: implement Prim logic
		return new Edge[0];
	}
}
