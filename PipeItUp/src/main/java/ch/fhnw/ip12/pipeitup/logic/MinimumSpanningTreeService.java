package ch.fhnw.ip12.pipeitup.logic;

import java.util.Set;

import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;
import ch.fhnw.ip12.pipeitup.logic.Models.VertexModel;

/**
* MinimumSpanningTree
*/
public interface MinimumSpanningTreeService {


	boolean canEdgeBeUsed(GraphLayoutModel graphLayout, EdgeModel edge);
	boolean isMspCompleted(GraphLayoutModel graphLayout);
	int usedWeightsSum(GraphLayoutModel graphLayout);
	Set<EdgeModel> getUnusedEdges(GraphLayoutModel graphLayout);
	Set<EdgeModel> getUsedEdges(GraphLayoutModel graphLayout);
	Set<EdgeModel> getEdgesConnectedToVertex(GraphLayoutModel graphLayout, VertexModel vertex);
	
}
