package ch.fhnw.ip12.pipeitup.logic;

import java.util.List;
import java.util.Random;

import com.google.inject.Inject;

import ch.fhnw.ip12.pipeitup.app.ExcludeMethodFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.data.GraphLayoutDataLoader;
import ch.fhnw.ip12.pipeitup.data.Models.Edge;
import ch.fhnw.ip12.pipeitup.data.Models.GraphLayout;
import ch.fhnw.ip12.pipeitup.data.Models.Vertex;
import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;
import ch.fhnw.ip12.pipeitup.logic.Models.VertexModel;

/**
 * GraphLayoutLoaderImpl
 */
class GraphLayoutLoaderImpl implements GraphLayoutLoader {

	private GraphLayoutDataLoader graphLayoutDataLoader;

	@Inject
	public GraphLayoutLoaderImpl(GraphLayoutDataLoader graphLayoutDataLoader) {
		this.graphLayoutDataLoader = graphLayoutDataLoader;
	}

	@Override
	public int[][] getIncidenceMatrixForGraph(int graphId) {
		return null;
	}

	@Override
	public GraphLayoutModel getRandomlyWeightedGraph(int maxWeight) {
		if (maxWeight < 1) {
			throw new IllegalArgumentException("maxWeight has to be at least 1");
		}

		GraphLayout graphLayout = graphLayoutDataLoader.getGraphLayoutFromDb();
		List<VertexModel> vertexModels = graphLayout.getVertices().stream().map(v -> Map(v)).toList();
		List<EdgeModel> edgeModels = graphLayout.getEdges().stream().map(e -> Map(e, vertexModels)).toList();

		Random randomGenerator = new Random();
		for (int i = 0; i < edgeModels.size(); i++) {
			edgeModels.get(i).setWeight(randomGenerator.nextInt(maxWeight) + 1);
		}

		return new GraphLayoutModel(vertexModels, edgeModels);
	}

	private static VertexModel Map(Vertex vertex) {
		return new VertexModel(vertex.getId(), vertex.getPositionX(), vertex.getPositionY());
	}

	@ExcludeMethodFromJacocoGeneratedReport
	private static EdgeModel Map(Edge edge, List<VertexModel> vertexList) {
		return new EdgeModel(vertexList.stream().filter(v -> v.getId() == edge.getVertex1().getId()).findFirst().get(),
				vertexList.stream().filter(v -> v.getId() == edge.getVertex2().getId()).findFirst().get(), 0);
	}
}
