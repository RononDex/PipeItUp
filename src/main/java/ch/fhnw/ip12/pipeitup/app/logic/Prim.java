package main.java.ch.fhnw.ip12.pipeitup.app.logic;

public class Prim extends MST{


	public Prim(int[][] incidentMatrix) {
		super(incidentMatrix);
	}

	@Override
	Edge[] nextPossibleEdges() {
		// TODO: implement Prim logic
		return new Edge[0];
	}
}
