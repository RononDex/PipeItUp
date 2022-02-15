package ch.fhnw.ip12.pipeitup.logic.Models;

import java.util.ArrayList;
import java.util.List;

/**
* GraphLayoutModel
*/
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
