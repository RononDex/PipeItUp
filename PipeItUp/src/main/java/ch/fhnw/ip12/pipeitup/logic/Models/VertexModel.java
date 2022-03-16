package ch.fhnw.ip12.pipeitup.logic.Models;

import java.util.Set;
import java.util.stream.Collectors;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;

/**
 * VertexModel
 */
@ExcludeTypeFromJacocoGeneratedReport
public class VertexModel {

	private double positionX;
	private double positionY;

	public VertexModel(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public double getPositionX() {
		return positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public Set<EdgeModel> getConnectedEdges(GraphLayoutModel graph) {
		return graph.edges.stream().filter(edge -> edge.getConnectedVertices().contains(this))
				.collect(Collectors.toSet());
	}

}
