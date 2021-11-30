package ch.fhnw.ip12.pipeitup.logic;

public class Vertex {
	private Edge[] incidenceEdges;
	private boolean visited;



	public Edge[] getIncidentEdges() {
		return incidenceEdges;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
}
