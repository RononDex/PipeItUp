package ch.fhnw.ip12.pipeitup.logic;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.IntStream;

public class Graph {
	private final LinkedHashSet<Edge> edges = new LinkedHashSet<>();
	private final Vertex[] vertices;

	public Graph(int[][] incidenceMatrix) {

		// instantiate vertices
		this.vertices = new Vertex[incidenceMatrix.length];
		for (int i = 0; i < vertices.length; i++) {
			this.vertices[i] = new Vertex();
		}

		// instantiate edges with vertices
		for (int i = 0; i < incidenceMatrix[0].length; i++) {
			Vertex[] startEndVertex = new Vertex[2];
			int k = 0;
			int weight = 0;
			int[] row = getColumn(incidenceMatrix, i);
			for (int j = 0; j < incidenceMatrix.length; j++) {
				assert k <= 2; // TODO: implement Matrix check at top of constructor
				if (row[j] != 0) {
					startEndVertex[k] = vertices[j];
					k++;
					weight = row[j];
				}
			}
			edges.add(new Edge(weight, startEndVertex));
		}
	}

	private static int[] getColumn(int[][] matrix, int column) {
		return IntStream.range(0, matrix.length)
				.map(i -> matrix[i][column]).toArray();
	}


	public LinkedHashSet<Edge> getEdges() {
		return edges;
	}

	public Vertex[] getVertices() {
		return vertices;
	}
}
