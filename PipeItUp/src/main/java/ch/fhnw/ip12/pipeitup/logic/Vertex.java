package ch.fhnw.ip12.pipeitup.logic;

import java.util.HashSet;

public class Vertex {
	private final HashSet<Edge> edges = new HashSet<>();

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
}
