package ch.fhnw.ip12.pipeitup.ui.views.gameboard;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;

import java.util.Set;

@ExcludeTypeFromJacocoGeneratedReport
public class GraphViewModel {

	public GraphViewModel(Set<VertexViewModel> vertices, Set<EdgeViewModel> edges) {
		this.vertexViewModels = vertices;
		this.edgeViewModels = edges;
	}

	public Set<VertexViewModel> vertexViewModels;

	public Set<EdgeViewModel> edgeViewModels;
}
