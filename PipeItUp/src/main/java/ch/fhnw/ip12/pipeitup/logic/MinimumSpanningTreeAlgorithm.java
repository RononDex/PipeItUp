package ch.fhnw.ip12.pipeitup.logic;

import ch.fhnw.ip12.pipeitup.logic.Models.EdgeModel;
import ch.fhnw.ip12.pipeitup.logic.Models.GraphLayoutModel;

/**
* MinimumSpanningTreeAlgorithm
*/
public interface MinimumSpanningTreeAlgorithm {

	/**
	 * Check if the edge can be used in the process of finding the mst There may be
	 * multiple possible edges to use.
	 *
	 * @param edge to check if it is a valid pick
	 * @return true if the given edge is a valid pick for the next step and can be used
	 */
	boolean isEdgeValidPick(GraphLayoutModel graphLayout, EdgeModel edge);
	
}
