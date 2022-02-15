package ch.fhnw.ip12.pipeitup.logic.Models;

/**
* EdgeModel
*/
public class EdgeModel {

	private VertexModel vertex1;
	private VertexModel vertex2;
	private int weight;

	public EdgeModel(VertexModel vertex1, VertexModel vertex2, int weight) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = weight;
	}

	public VertexModel getVertex1() {
		return vertex1;
	}

	public VertexModel getVertex2() {
		return vertex2;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
