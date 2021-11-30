package ch.fhnw.ip12.pipeitup.logic;

public class Edge {
	private int weight;
	private Vertex[] vertices = new Vertex[2];
	private boolean used = false;

	public int getWeight() {
		return weight;
	}

	public Vertex[] getVertices() {
		return vertices;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}
}
