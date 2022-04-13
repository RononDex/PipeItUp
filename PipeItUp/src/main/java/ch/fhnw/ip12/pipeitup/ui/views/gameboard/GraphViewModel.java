package ch.fhnw.ip12.pipeitup.ui.views.gameboard;

import java.util.HashSet;
import java.util.Set;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

@ExcludeTypeFromJacocoGeneratedReport
public class GraphViewModel {

	public GraphViewModel(Set<VertexViewModel> vertices, Set<EdgeViewModel> edges) {
		this.vertexViewModels = vertices;
		this.edgeViewModels = edges;
	}

	public Set<VertexViewModel> vertexViewModels;

	public Set<EdgeViewModel> edgeViewModels;
}
