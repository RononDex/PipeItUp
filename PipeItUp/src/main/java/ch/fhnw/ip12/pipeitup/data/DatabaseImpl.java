package ch.fhnw.ip12.pipeitup.data;

import ch.fhnw.ip12.pipeitup.app.ExcludeMethodFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.app.PipeItUp;
import ch.fhnw.ip12.pipeitup.data.Models.Edge;
import ch.fhnw.ip12.pipeitup.data.Models.GraphLayout;
import ch.fhnw.ip12.pipeitup.data.Models.Vertex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.HashSet;

class DatabaseImpl implements Database {

	static final Logger log = LogManager.getLogger(PipeItUp.class.getName());

	private Connection connect() {
		String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/../database.db";
		System.out.println(url);;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return conn;
	}

	@Override
	public GraphLayout getGraphLayout() {
		ArrayList<Vertex> vertexList = new ArrayList<>();
		ArrayList<Edge> edgeList = new ArrayList<>();

		String sqlVertex = "SELECT id_vertex, positionX, positionY FROM tbl_vertex";
		String sqlGraphLayout = "SELECT firstVertex, secondVertex FROM tbl_graphlayout";

		try (Connection conn = this.connect();
			 Statement stmtVertex = conn.createStatement();
			 ResultSet rsVertex = stmtVertex.executeQuery(sqlVertex);
			 Statement stmtGraphLayout = conn.createStatement();
			 ResultSet rsGraphLayout = stmtGraphLayout.executeQuery(sqlGraphLayout)) {

			while (rsVertex.next()) {
				vertexList.add(new Vertex(rsVertex.getInt("id_vertex"), rsVertex.getInt("positionX"),
					rsVertex.getInt("positionY")));
			}
			while (rsGraphLayout.next()) {
				edgeList.add(new Edge(vertexList.get(rsGraphLayout.getInt("firstVertex")),
					vertexList.get(rsGraphLayout.getInt("secondVertex"))));
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}

		return new GraphLayout(new HashSet<>(vertexList), new HashSet<>(edgeList));
	}

	@Override
	@ExcludeMethodFromJacocoGeneratedReport
	public boolean saveScore(String name, int score) {
		String sql = "INSERT INTO tbl_highscore(name,score) VALUES(?,?)";
		try (Connection conn = this.connect();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setDouble(2, score);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return false;
	}

	@Override
	@ExcludeMethodFromJacocoGeneratedReport
	public void getScores() {
		String sql = "SELECT name, score FROM tbl_highscore";
		try (Connection conn = this.connect();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				System.out.println(rs.getString("name") + "\t" +
					rs.getInt("score"));
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}
}
