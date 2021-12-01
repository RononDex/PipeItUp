package ch.fhnw.ip12.pipeitup.logic;

import java.util.HashSet;

public class Vertex {
	private final HashSet<Edge> edges = new HashSet<>();
	private boolean visited = false;

	public Vertex() {

	}

	public HashSet<Edge> getEdges() {
		return edges;
	}
	public boolean addEdge(Edge edge){
		if (!edges.contains(edge)){
			edges.add(edge);
			return true;
		}
		return false;
	}

	void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isVisited() {
		return visited;
	}
}
