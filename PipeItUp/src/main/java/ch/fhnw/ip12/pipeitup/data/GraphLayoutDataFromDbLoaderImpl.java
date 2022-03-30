package ch.fhnw.ip12.pipeitup.data;

import java.util.ArrayList;
import java.util.HashSet;

import com.google.inject.Inject;

import ch.fhnw.ip12.pipeitup.data.Models.Edge;
import ch.fhnw.ip12.pipeitup.data.Models.GraphLayout;
import ch.fhnw.ip12.pipeitup.data.Models.Vertex;


/**
 * GraphLayoutFromDbLoaderImpl
 */
class GraphLayoutDataFromDbLoaderImpl implements GraphLayoutDataLoader {
	Database db;

	@Inject
	public GraphLayoutDataFromDbLoaderImpl() {
		db = new DatabaseImpl();
	}

	@Override
	public GraphLayout getGraphLayoutFromDb() {
		return db.getGraphLayout();
	}

}
