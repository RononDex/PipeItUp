package ch.fhnw.ip12.pipeitup.logic.Models;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;

/**
* VertexModel
*/
@ExcludeTypeFromJacocoGeneratedReport
public class VertexModel {

	private int id;
	private double positionX;
	private double positionY;

	public VertexModel(int id, double positionX, double positionY) {
		this.id = id;
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public int getId() {
		return id;
	}

	public double getPositionX() {
		return positionX;
	}

	public double getPositionY() {
		return positionY;
	}
}
