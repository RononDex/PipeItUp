package ch.fhnw.ip12.pipeitup.logic;

/**
* GraphLayoutLoader
*/
public interface GraphLayoutLoader {

	Graph getGraph(int graphId);

	Graph getRandomGraph();
	
}
