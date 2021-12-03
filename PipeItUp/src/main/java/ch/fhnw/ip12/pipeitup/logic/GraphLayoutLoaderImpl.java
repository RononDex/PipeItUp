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
	
}
