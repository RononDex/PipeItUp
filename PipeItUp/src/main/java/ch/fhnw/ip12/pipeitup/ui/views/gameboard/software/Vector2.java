package ch.fhnw.ip12.pipeitup.ui.views.gameboard.software;

public class Vector2 {
	// Members
	public double x;
	public double y;

	// Constructors
	public Vector2() {
		this.x = 0.0f;
		this.y = 0.0f;
	}

	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// Compare two vectors
	public boolean equals(Vector2 other) {
		return (this.x == other.x && this.y == other.y);
	}

	public double distance(Vector2 b) {
		double v0 = b.x - this.x;
		double v1 = b.y - this.y;
		return Math.sqrt(v0 * v0 + v1 * v1);
	}

	public Vector2 add(Vector2 b) {
		return new Vector2(this.x + b.x, this.y + b.y);
	}

	public Vector2 subtract(Vector2 b) {
		return new Vector2(this.x - b.x, this.y - b.y);
	}

	public Vector2 multiply(double x) {
		return new Vector2(this.x * x, this.y * x);
	}

	public Vector2 divide(double x) {
		return new Vector2(this.x / x, this.y / x);
	}

	public double length() {
		return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
	}

	public Vector2 normalize() {
		double length = length();
		return new Vector2(this.x / length, this.y / length);
	}

	public static double dot(Vector2 a, Vector2 b) {
		return a.x * b.x + a.y * b.y;
	}

	public Vector2 rotate(double angleInRadians) {
		return new Vector2(
				this.x * Math.cos(angleInRadians) + this.y * Math.sin(angleInRadians),
				(-1) * this.x * Math.sin(angleInRadians) + this.y * Math.cos(angleInRadians));
	}
}
