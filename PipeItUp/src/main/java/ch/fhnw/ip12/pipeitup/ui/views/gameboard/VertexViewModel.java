package ch.fhnw.ip12.pipeitup.ui.views.gameboard;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.Color;

@ExcludeTypeFromJacocoGeneratedReport
public class VertexViewModel {

	private int vertexNumber = 0;

	private double vertexCenterPositionXInMm = 0d;
	private double vertexCenterPositionYInMm = 0d;
	private int LED;
	private int LEDLine;

	public VertexViewModel(int vertexNumber, double vertexCenterPositionXInMm, double vertexCenterPositionYInMm, int LED, int LEDLine) {
		this.vertexNumber = vertexNumber;
		this.vertexCenterPositionXInMm = vertexCenterPositionXInMm;
		this.vertexCenterPositionYInMm = vertexCenterPositionYInMm;
		this.LED = LED;
		this.LEDLine = LEDLine;
	}

	private boolean isLedActive = false;

	private Color ledColor = new Color(255, 0, 0);

	public int getVertexNumber() {
		return vertexNumber;
	}

	public double getVertexCenterPositionXInMm() {
		return vertexCenterPositionXInMm;
	}

	public double getVertexCenterPositionYInMm() {
		return vertexCenterPositionYInMm;
	}

	public boolean getIsLedActive() {
		return isLedActive;
	}

	public Color getLedColor() {
		return ledColor;
	}

	public int getLED() {
		return LED;
	}

	public void setLED(int LED) {
		this.LED = LED;
	}

	public int getLEDLine() {
		return LEDLine;
	}

	public void setLEDLine(int LEDLine) {
		this.LEDLine = LEDLine;
	}
}

