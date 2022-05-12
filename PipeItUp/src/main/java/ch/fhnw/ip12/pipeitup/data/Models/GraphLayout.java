package ch.fhnw.ip12.pipeitup.data.Models;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;

import java.util.Set;

@ExcludeTypeFromJacocoGeneratedReport
public class GraphLayout {

	private Set<Vertex> vertices;

	private Set<Edge> edges;

	public GraphLayout(Set<Vertex> vertices, Set<Edge> edges) {
		super();

		this.vertices = vertices;
		this.edges = edges;
	}

	public Set<Vertex> getVertices() {
		return vertices;
	}

	public Set<Edge> getEdges() {
		return edges;
	}
}
