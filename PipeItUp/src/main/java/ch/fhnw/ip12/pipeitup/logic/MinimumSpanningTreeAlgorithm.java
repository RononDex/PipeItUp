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
	 * @param edge to check usability for
	 * @return true if next possible edge contains the given edge
	 */
	boolean isNextEdge(GraphLayoutModel graphLayout, EdgeModel edge);
	
}
