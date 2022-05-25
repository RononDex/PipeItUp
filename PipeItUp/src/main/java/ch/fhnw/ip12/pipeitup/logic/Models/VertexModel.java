package ch.fhnw.ip12.pipeitup.logic.Models;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * VertexModel
 */
@ExcludeTypeFromJacocoGeneratedReport
public class VertexModel {

	private double positionX;
	private double positionY;
	private int LED;
	private int LEDLine;

	public VertexModel(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}
	public VertexModel(double positionX, double positionY, int LED, int LEDLine) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.LED = LED;
		this.LEDLine = LEDLine;
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

	public int getLED() {
		return LED;
	}

	public int getLEDLine() {
		return LEDLine;
	}
}
