package ch.fhnw.ip12.pipeitup.logic;

import java.util.*;

/*
minimum spanning tree
 */
public abstract class MinimumSpanningTree {

	private final HashSet<Edge> unusedEdges;
	private final HashSet<Edge> tree = new HashSet<>();
	private final Vertex[] allVertices;
	private final HashSet<Vertex> visitedVertices = new HashSet<>();

	public MinimumSpanningTree(Graph graph) {
		this.unusedEdges = graph.getEdges();
		this.allVertices = graph.getVertices();
	}


	/**
	 * Check if the edge can be used in the process of finding the mst
	 * There may be multiple possible edges to use.
	 *
	 * @param edge to check usability for
	 * @return true if next possible edge contains the given edge
	 */
	abstract boolean isNextEdge(Edge edge);

	public boolean EdgeIsUsable(Edge edge) {
		if (!unusedEdges.contains(edge) || tree.contains(edge)) return false;
		Vertex vertex1 = edge.getVertices()[0];
		Vertex vertex2 = edge.getVertices()[1];
		return !(visitedVertices.contains(vertex1) && visitedVertices.contains(vertex2)); // check cycle
	}

	/**
	 * check if all vertices are used
	 *
	 * @return true if all vertices are used, otherwise false
	 */
	public boolean completed() {
		return new HashSet<>(Arrays.asList(allVertices)).equals(visitedVertices);
	}

	public int sum() {
		// TODO: return sum of edge weights, only when all vertices are used.
		int sum = 0;
		for (Edge edge :
				tree) {
			sum += edge.getWeight();
		}
		return sum;
	}
	public boolean addEdgeToTree(Edge edge){
		if (isNextEdge(edge)){
			tree.add(edge);
			visitedVertices.add(edge.getVertices()[0]);
			visitedVertices.add(edge.getVertices()[1]);
			unusedEdges.remove(edge);
			return true;
		}
		return false;
	}

	public HashSet<Edge> getUnusedEdges() {
		return unusedEdges;
	}

	public HashSet<Edge> getTreeProgress() {
		return tree;
	}
}
