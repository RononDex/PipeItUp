package ch.fhnw.ip12.pipeitup.ui.views.gameboard;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

@ExcludeTypeFromJacocoGeneratedReport
public class GraphViewModel {

	Property<List<VertexViewModel>> VertexViewModels = new SimpleObjectProperty<List<VertexViewModel>>(
			new ArrayList<VertexViewModel>());

	Property<List<EdgeViewModel>> EdgeViewModels = new SimpleObjectProperty<List<EdgeViewModel>>(
			new ArrayList<EdgeViewModel>());

	public Property<List<VertexViewModel>> getVertexViewModels() {
		return VertexViewModels;
	}

	public Property<List<EdgeViewModel>> getEdgeViewModels() {
		return EdgeViewModels;
	}
}
