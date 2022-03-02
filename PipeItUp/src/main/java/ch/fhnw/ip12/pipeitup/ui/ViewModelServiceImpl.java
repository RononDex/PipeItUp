package ch.fhnw.ip12.pipeitup.ui;

import java.util.List;
import java.util.stream.Collectors;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import com.google.inject.Inject;

import ch.fhnw.ip12.pipeitup.app.ExcludeMethodFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.logic.GraphLayoutLoader;
import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;
import ch.fhnw.ip12.pipeitup.logic.Models.VertexModel;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.VertexViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchViewModel;

/**
 * ViewModelService
 */
@ExcludeTypeFromJacocoGeneratedReport
class ViewModelServiceImpl implements ViewModelService {

	private GraphLayoutLoader graphLayoutLoader;

	@Inject
	public ViewModelServiceImpl(GraphLayoutLoader graphLayoutLoader) {
		this.graphLayoutLoader = graphLayoutLoader;
	}

	public PipeItUpGameViewModel createStartUpViewModel() {
		PipeItUpGameViewModel viewModel = new PipeItUpGameViewModel();
		viewModel.touchViewModel = new TouchViewModel();

		GraphLayoutModel randomGraph = graphLayoutLoader.getRandomlyWeightedGraph(6);

		int nodeCounter = 0;

		List<VertexViewModel> vertexViewModels = randomGraph.getVertices().stream().map(vertex -> Map(vertex, nodeCounter))
				.collect(Collectors.toList());
		List<EdgeViewModel> edgeViewModels = randomGraph.getEdges().stream().map(e -> Map(e, vertexViewModels))
				.collect(Collectors.toList());

		viewModel.gameBoardViewModel.getGraphViewModel().getValue().getEdgeViewModels().setValue(edgeViewModels);
		viewModel.gameBoardViewModel.getGraphViewModel().getValue().getVertexViewModels().setValue(vertexViewModels);

		return viewModel;
	}

	@ExcludeMethodFromJacocoGeneratedReport
	private static VertexViewModel Map(VertexModel vertex, int nodeCounter) {
		nodeCounter++;
		return new VertexViewModel(nodeCounter, vertex.getPositionX(), vertex.getPositionY());
	}

	@ExcludeMethodFromJacocoGeneratedReport
	private static EdgeViewModel Map(EdgeModel edge, List<VertexViewModel> vertexList) {
		return new EdgeViewModel(
				vertexList.stream().filter(v -> v.getVertexCenterPositionXInMm().get() == edge.getVertex1().getPositionX() && v.getVertexCenterPositionYInMm().get() == edge.getVertex1().getPositionY()).findFirst().get(),
				vertexList.stream().filter(v -> v.getVertexCenterPositionXInMm().get() == edge.getVertex2().getPositionX() && v.getVertexCenterPositionYInMm().get() == edge.getVertex2().getPositionY()).findFirst().get(),
				edge.getWeight());
	}

}
