package ch.fhnw.ip12.pipeitup.data;

import java.util.ArrayList;

import com.google.inject.Inject;

import ch.fhnw.ip12.pipeitup.data.Models.Edge;
import ch.fhnw.ip12.pipeitup.data.Models.GraphLayout;
import ch.fhnw.ip12.pipeitup.data.Models.Vertex;

/**
* GraphLayoutFromDbLoaderImpl
*/
class GraphLayoutDataFromDbLoaderImpl implements GraphLayoutDataLoader {

	@Inject
	public GraphLayoutDataFromDbLoaderImpl() {
	}

	@Override
	public GraphLayout getGraphLayoutFromDb() {
		// TODO: Load data from db instead of hardcoded
		
		ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
		vertexList.add(new Vertex(1, 154, 30));
		vertexList.add(new Vertex(2, 288, 30));
		vertexList.add(new Vertex(3, 423, 30));
		vertexList.add(new Vertex(4, 37, 97));
		vertexList.add(new Vertex(5, 154, 165));
		vertexList.add(new Vertex(6, 288, 165));
		vertexList.add(new Vertex(7, 423, 165));
		vertexList.add(new Vertex(8, 37, 232));
		vertexList.add(new Vertex(9, 86, 281));
		vertexList.add(new Vertex(10, 221, 281));
		vertexList.add(new Vertex(11, 356, 281));
		vertexList.add(new Vertex(12, 154, 398));
		vertexList.add(new Vertex(13, 288, 398));
		vertexList.add(new Vertex(14, 86, 514));
		vertexList.add(new Vertex(15, 221, 514));
		vertexList.add(new Vertex(16, 356, 514));

		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		edgeList.add(new Edge(vertexList.get(0), vertexList.get(1)));
		edgeList.add(new Edge(vertexList.get(0), vertexList.get(4)));
		edgeList.add(new Edge(vertexList.get(0), vertexList.get(3)));
		edgeList.add(new Edge(vertexList.get(1), vertexList.get(2)));
		edgeList.add(new Edge(vertexList.get(1), vertexList.get(5)));
		edgeList.add(new Edge(vertexList.get(2), vertexList.get(6)));
		edgeList.add(new Edge(vertexList.get(3), vertexList.get(4)));
		edgeList.add(new Edge(vertexList.get(3), vertexList.get(7)));
		edgeList.add(new Edge(vertexList.get(4), vertexList.get(5)));
		edgeList.add(new Edge(vertexList.get(4), vertexList.get(8)));
		edgeList.add(new Edge(vertexList.get(5), vertexList.get(6)));
		edgeList.add(new Edge(vertexList.get(5), vertexList.get(9)));
		edgeList.add(new Edge(vertexList.get(5), vertexList.get(10)));
		edgeList.add(new Edge(vertexList.get(6), vertexList.get(10)));
		edgeList.add(new Edge(vertexList.get(8), vertexList.get(9)));
		edgeList.add(new Edge(vertexList.get(8), vertexList.get(11)));
		edgeList.add(new Edge(vertexList.get(9), vertexList.get(10)));
		edgeList.add(new Edge(vertexList.get(9), vertexList.get(11)));
		edgeList.add(new Edge(vertexList.get(11), vertexList.get(12)));
		edgeList.add(new Edge(vertexList.get(11), vertexList.get(13)));
		edgeList.add(new Edge(vertexList.get(11), vertexList.get(14)));
		edgeList.add(new Edge(vertexList.get(12), vertexList.get(14)));
		edgeList.add(new Edge(vertexList.get(12), vertexList.get(15)));
		edgeList.add(new Edge(vertexList.get(13), vertexList.get(14)));
		edgeList.add(new Edge(vertexList.get(14), vertexList.get(15)));

		return new GraphLayout(vertexList, edgeList);
	}

}
