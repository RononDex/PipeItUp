package ch.fhnw.ip12.pipeitup.ui.views.gameboard;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class EdgeViewModel {
	public VertexViewModel vertex1;

	public VertexViewModel vertex2;

	public SimpleIntegerProperty weight = new SimpleIntegerProperty(1);

	public SimpleObjectProperty<Color> color = new SimpleObjectProperty<Color>(Color.GREEN);

	public SimpleObjectProperty<EdgeState> edgeState = new SimpleObjectProperty<EdgeState>(EdgeState.UNSELECTED);

	public EdgeViewModel(VertexViewModel vertex1, VertexViewModel vertex2) {
		super();
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
	}
}

