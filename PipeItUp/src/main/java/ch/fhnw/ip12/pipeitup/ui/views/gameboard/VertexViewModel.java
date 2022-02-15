package ch.fhnw.ip12.pipeitup.ui.views.gameboard;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.Color;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

@ExcludeTypeFromJacocoGeneratedReport
public class VertexViewModel {

	private IntegerProperty vertexNumber = new SimpleIntegerProperty();

	private DoubleProperty vertexCenterPositionXInMm = new SimpleDoubleProperty();
	private DoubleProperty vertexCenterPositionYInMm = new SimpleDoubleProperty();

	public VertexViewModel(int vertexNumber, double vertexCenterPositionXInMm, double vertexCenterPositionYInMm) {
		this.vertexNumber.set(vertexNumber);
		this.vertexCenterPositionXInMm.set(vertexCenterPositionXInMm);
		this.vertexCenterPositionYInMm.set(vertexCenterPositionYInMm);
	}

	private BooleanProperty isLedActive = new SimpleBooleanProperty();

	private Property<Color> ledColor = new SimpleObjectProperty<Color>();

	public IntegerProperty getVertexNumber() {
		return vertexNumber;
	}

	public DoubleProperty getVertexCenterPositionXInMm() {
		return vertexCenterPositionXInMm;
	}

	public DoubleProperty getVertexCenterPositionYInMm() {
		return vertexCenterPositionYInMm;
	}

	public BooleanProperty getIsLedActive() {
		return isLedActive;
	}

	public Property<Color> getLedColor() {
		return ledColor;
	}
}

