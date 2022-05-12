package ch.fhnw.ip12.pipeitup.data.Models;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;

@ExcludeTypeFromJacocoGeneratedReport
public class Edge {

	private Vertex vertex1;
	private Vertex vertex2;

	public Edge(Vertex vertex1, Vertex vertex2) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
	}

	public Vertex getVertex1() {
		return vertex1;
	}

	public Vertex getVertex2() {
		return vertex2;
	}

}
