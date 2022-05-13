package ch.fhnw.ip12.pipeitup.data.Models;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;

@ExcludeTypeFromJacocoGeneratedReport
public class Vertex {
	private int id;
	private double positionX;
	private double positionY;
	private int LED;
	private int LEDLine;

	public Vertex(int id, double positionX, double positionY, int LED, int LEDLine) {
		this.id = id;
		this.positionX = positionX;
		this.positionY = positionY;
		this.LED = LED;
		this.LEDLine = LEDLine;
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

	public int getLED() {
		return LED;
	}

	public int getLEDLine() {
		return LEDLine;
	}
}

