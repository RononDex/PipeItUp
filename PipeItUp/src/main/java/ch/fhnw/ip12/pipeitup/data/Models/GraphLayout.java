package ch.fhnw.ip12.pipeitup.data.Models;

import java.util.ArrayList;
import java.util.List;

/**
* Graphlayout
*/
public class GraphLayout {

	private List<Vertex> vertices;

	private List<Edge> edges;

	public GraphLayout(List<Vertex> vertices, List<Edge> edges) {
		super();

		this.vertices = vertices;
		this.edges = edges;
	}

	public List<Vertex> getVertices() {
		return vertices;
	}

	public List<Edge> getEdges() {
		return edges;
	}
}
