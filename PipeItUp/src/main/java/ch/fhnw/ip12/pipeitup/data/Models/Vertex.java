package ch.fhnw.ip12.pipeitup.data.Models;

public class Vertex {
	private int id;
	private double positionX;
	private double positionY;

	public Vertex(int id, double positionX, double positionY) {
		super();
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

