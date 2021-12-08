package ch.fhnw.ip12.pipeitup.logic;

/**
* GraphLayoutLoader
*/
public interface GraphLayoutLoader {

	int[][] getIncidenceMatrixForGraph(int graphId);

	int[][] getRandomIncidenceMatrix();
	
}
