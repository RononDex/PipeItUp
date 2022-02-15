package ch.fhnw.ip12.pipeitup.ui.views.gameboard;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.Color;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

@ExcludeTypeFromJacocoGeneratedReport
public class EdgeViewModel {
	public VertexViewModel vertex1;

	public VertexViewModel vertex2;

	public SimpleIntegerProperty weight = new SimpleIntegerProperty(1);

	public SimpleObjectProperty<Color> color = new SimpleObjectProperty<Color>(new Color(0, 255, 0));

	public SimpleObjectProperty<EdgeState> edgeState = new SimpleObjectProperty<EdgeState>(EdgeState.UNSELECTED);

	public EdgeViewModel(VertexViewModel vertex1, VertexViewModel vertex2, int weight) {
		super();
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight.set(weight);
	}
}

