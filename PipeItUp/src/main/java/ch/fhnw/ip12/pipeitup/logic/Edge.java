package ch.fhnw.ip12.pipeitup.logic;

import java.util.HashSet;

public class Edge {
	private final int weight;
	private final HashSet<Vertex> vertices;
	private boolean used = false;

	public Edge(int weight, HashSet<Vertex> vertices) {
		this.weight = weight;
		this.vertices = vertices;
	}

	public int getWeight() {
		return weight;
	}

	public HashSet<Vertex> getVertices() {
		return vertices;
	}

	public boolean isUsed() {
		return used;
	}

	void setUsed(boolean used) {
		this.used = used;
	}
}
