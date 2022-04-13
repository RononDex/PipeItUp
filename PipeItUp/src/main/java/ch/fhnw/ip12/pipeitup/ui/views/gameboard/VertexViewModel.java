package ch.fhnw.ip12.pipeitup.ui.views.gameboard;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.Color;

@ExcludeTypeFromJacocoGeneratedReport
public class VertexViewModel {

	private int vertexNumber = 0;

	private double vertexCenterPositionXInMm = 0d;
	private double vertexCenterPositionYInMm = 0d;

	public VertexViewModel(int vertexNumber, double vertexCenterPositionXInMm, double vertexCenterPositionYInMm) {
		this.vertexNumber = vertexNumber;
		this.vertexCenterPositionXInMm = vertexCenterPositionXInMm;
		this.vertexCenterPositionYInMm = vertexCenterPositionYInMm;
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
}

