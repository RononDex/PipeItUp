package ch.fhnw.ip12.pipeitup.logic;

import com.google.inject.Inject;

import ch.fhnw.ip12.pipeitup.data.GraphLayoutDataLoader;

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
	public int[][] getIncidenceMatrixForGraph(int graphId) {
		Object[] graphLayout = graphLayoutDataLoader.getData();
		return null;
	}

	@Override
	public int[][] getRandomIncidenceMatrix() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
