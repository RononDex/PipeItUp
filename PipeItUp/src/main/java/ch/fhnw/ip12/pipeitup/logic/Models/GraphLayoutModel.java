package ch.fhnw.ip12.pipeitup.logic.Models;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;

import java.util.Set;

/**
 * GraphLayoutModel
 */
@ExcludeTypeFromJacocoGeneratedReport
public class GraphLayoutModel {

	Set<VertexModel> vertices;
	Set<EdgeModel> edges;

	VertexModel startVertexForPrim;

	public GraphLayoutModel(Set<VertexModel> vertices, Set<EdgeModel> edges) {
		this.vertices = vertices;
		this.edges = edges;
	}

	public Set<VertexModel> getVertices() {
		return vertices;
	}

	public Set<EdgeModel> getEdges() {
		return edges;
	}

	public VertexModel getStartVertexForPrim() {
		return startVertexForPrim;
	}

	public void setStartVertexForPrim(VertexModel startVertexForPrim) {
		this.startVertexForPrim = startVertexForPrim;
	}
}
