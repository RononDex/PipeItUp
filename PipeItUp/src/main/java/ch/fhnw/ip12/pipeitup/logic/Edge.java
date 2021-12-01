package ch.fhnw.ip12.pipeitup.logic;

public class Edge {
	private final int weight;
	private final Vertex[] vertices;
	// private boolean used = false;

	public Edge(int weight, Vertex[] vertices) {
		this.weight = weight;
		this.vertices = vertices;
	}

	public int getWeight() {
		return weight;
	}

	public Vertex[] getVertices() {
		return vertices;
	}

//	public boolean isUsed() {
//		return used;
//	}
//
//	public void setUsed(boolean used) {
//		this.used = used;
//	}
}
