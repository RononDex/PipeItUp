package ch.fhnw.ip12.pipeitup.logic;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
	private final LinkedHashSet<Edge> edges;

	Graph(LinkedHashSet<Edge> edges) {
		this.edges = edges;
	}

	public static Graph fromIncidentMatrix(int[][] incidenceMatrix){
		LinkedHashSet<Edge> edges = new LinkedHashSet<>();
		// instantiate vertices
		Vertex[] vertices = new Vertex[incidenceMatrix.length];
		for (int i = 0; i < vertices.length; i++) {
			vertices[i] = new Vertex();
		}

		// instantiate edges with vertices
		for (int i = 0; i < incidenceMatrix[0].length; i++) { // TODO: implement Matrix check at top of constructor
			Vertex[] startEndVertex = new Vertex[2];
			int k = 0;
			int weight = 0;
			int[] row = getColumn(incidenceMatrix, i);
			for (int j = 0; j < incidenceMatrix.length; j++) {
				if (row[j] != 0) {
					startEndVertex[k] = (vertices[j]);
					k++;
					weight = row[j];
				}
			}
			Edge edge = new Edge(weight, new HashSet<>(
					List.of(startEndVertex)
			));
			edges.add(edge);
			startEndVertex[0].addEdge(edge);
			startEndVertex[1].addEdge(edge);
		}
		return new Graph(edges);
	}

	private static int[] getColumn(int[][] matrix, int column) {
		return Arrays.stream(matrix).mapToInt(ints -> ints[column]).toArray();
	}


	public LinkedHashSet<Edge> getEdges() {
		return edges;
	}

	public HashSet<Vertex> getVertices() {
		return edges.stream()
				.flatMap(edge -> edge.getVertices().stream())
				.collect(Collectors.toCollection(HashSet::new));
	}
}
