package ch.fhnw.ip12.pipeitup.logic;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

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

	private final GraphLayoutDataLoader graphLayoutDataLoader;

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
		Set<VertexModel> vertexModels = graphLayout.getVertices().stream().map(GraphLayoutLoaderImpl::Map)
				.collect(Collectors.toSet());
		Set<EdgeModel> edgeModels = graphLayout.getEdges().stream().map(e -> Map(e, vertexModels))
				.collect(Collectors.toSet());

		Random randomGenerator = new Random();
		for (EdgeModel edgeModel : edgeModels) {
			edgeModel.setWeight(randomGenerator.nextInt(maxWeight) + 1);
		}

		return new GraphLayoutModel(vertexModels, edgeModels);
	}

	private static VertexModel Map(Vertex vertex) {
		return new VertexModel(vertex.getPositionX(), vertex.getPositionY());
	}

	@ExcludeMethodFromJacocoGeneratedReport
	private static EdgeModel Map(Edge edge, Set<VertexModel> vertexList) {
		return new EdgeModel(
				vertexList.stream()
						.filter(v -> v.getPositionX() == edge.getVertex1().getPositionX()
								&& v.getPositionY() == edge.getVertex1().getPositionY())
						.findFirst().get(),
				vertexList.stream().filter(v -> v.getPositionX() == edge.getVertex2().getPositionX()
						&& v.getPositionY() == edge.getVertex2().getPositionY()).findFirst().get(),
				0);
	}
}
