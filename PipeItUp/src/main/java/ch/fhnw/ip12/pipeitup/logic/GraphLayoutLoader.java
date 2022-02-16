package ch.fhnw.ip12.pipeitup.logic;

import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;

/**
* GraphLayoutLoader
*/
public interface GraphLayoutLoader {

	int[][] getIncidenceMatrixForGraph(int graphId);

	GraphLayoutModel getRandomlyWeightedGraph(int maxWeight);
}
