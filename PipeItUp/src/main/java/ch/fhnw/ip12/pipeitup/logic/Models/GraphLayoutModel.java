package ch.fhnw.ip12.pipeitup.logic.Models;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;

import java.util.List;
import java.util.stream.Collectors;

/**
 * GraphLayoutModel
 */
@ExcludeTypeFromJacocoGeneratedReport
public class GraphLayoutModel {

	List<VertexModel> vertices;
	List<EdgeModel> edges;

	public GraphLayoutModel(List<VertexModel> vertices, List<EdgeModel> edges) {
		this.vertices = vertices;
		this.edges = edges;
	}

	public List<VertexModel> getVertices() {
		return vertices;
	}

	public List<EdgeModel> getEdges() {
		return edges;
	}
}
