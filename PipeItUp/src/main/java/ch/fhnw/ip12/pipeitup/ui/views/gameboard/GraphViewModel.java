package ch.fhnw.ip12.pipeitup.ui.views.gameboard;

import java.util.HashSet;
import java.util.Set;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

@ExcludeTypeFromJacocoGeneratedReport
public class GraphViewModel {

	Property<Set<VertexViewModel>> VertexViewModels = new SimpleObjectProperty<Set<VertexViewModel>>(
			new HashSet<VertexViewModel>());

	Property<Set<EdgeViewModel>> EdgeViewModels = new SimpleObjectProperty<Set<EdgeViewModel>>(
			new HashSet<EdgeViewModel>());

	public Property<Set<VertexViewModel>> getVertexViewModels() {
		return VertexViewModels;
	}

	public Property<Set<EdgeViewModel>> getEdgeViewModels() {
		return EdgeViewModels;
	}
}
