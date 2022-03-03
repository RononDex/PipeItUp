package ch.fhnw.ip12.pipeitup.logic.utils;

import java.util.ArrayList;
import java.util.HashSet;

import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;
import ch.fhnw.ip12.pipeitup.logic.Models.VertexModel;

/**
 * GraphLayoutBuilder
 */
public class GraphLayoutBuilder {

	ArrayList<VertexModel> vertices = new ArrayList<VertexModel>();
	ArrayList<EdgeModel> edges = new ArrayList<EdgeModel>();
	VertexModel startVertexForPrim;

	public GraphLayoutBuilder withVertex(VertexModel vertexModel) {
		vertices.add(vertexModel);
		return this;
	}

	public GraphLayoutBuilder withVertex(double posX, double posY) {
		vertices.add(new VertexModel(posX, posY));
		return this;
	}

	public GraphLayoutBuilder withEdge(EdgeModel edge) {
		edges.add(edge);
		return this;
	}

	public GraphLayoutBuilder withEdge(int vertex1Index, int vertex2Index, int weight) {
		edges.add(new EdgeModel(vertices.toArray(new VertexModel[vertices.size()])[vertex1Index],
				vertices.toArray(new VertexModel[vertices.size()])[vertex2Index], weight));
		return this;
	}

	public GraphLayoutBuilder withStartVertexForPrim(VertexModel startVertex) {
		startVertexForPrim = startVertex;
		return this;
	}

	public GraphLayoutBuilder withStartVertexForPrim(int vertexIndex) {
		startVertexForPrim = vertices.toArray(new VertexModel[vertices.size()])[vertexIndex];
		return this;
	}

	public GraphLayoutModel build() {
		GraphLayoutModel graph = new GraphLayoutModel(new HashSet<>(vertices), new HashSet<>(edges));
		graph.setStartVertexForPrim(startVertexForPrim);
		return graph;
	}
}
