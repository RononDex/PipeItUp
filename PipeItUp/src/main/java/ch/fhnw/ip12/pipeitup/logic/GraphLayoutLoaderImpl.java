package ch.fhnw.ip12.pipeitup.logic;

import com.google.inject.Inject;

import ch.fhnw.ip12.pipeitup.data.GraphLayoutDataLoader;

import java.util.HashSet;

/**
* GraphLayoutLoaderImpl
*/
public class GraphLayoutLoaderImpl implements GraphLayoutLoader {

	private GraphLayoutDataLoader graphLayoutDataLoader;

	@Inject
	public GraphLayoutLoaderImpl(GraphLayoutDataLoader graphLayoutDataLoader) {
		this.graphLayoutDataLoader = graphLayoutDataLoader;
	}

	@Override
	public Graph getGraph(int graphId) {
		int[][] graphLayout = graphLayoutDataLoader.getData();
		return Graph.fromIncidentMatrix(graphLayout);
	}

	@Override
	public Graph getRandomGraph() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
