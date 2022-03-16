package ch.fhnw.ip12.pipeitup.logic;


import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;

/**
* MinimumSpanningTree
*/
public interface MinimumSpanningTreeService {

	boolean canEdgeBeUsed(GraphLayoutModel graphLayout, EdgeModel edge);
	boolean isMspCompleted(GraphLayoutModel graphLayout);
	int usedWeightsSum(GraphLayoutModel graphLayout);
}
