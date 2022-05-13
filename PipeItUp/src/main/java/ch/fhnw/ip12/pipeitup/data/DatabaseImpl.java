package ch.fhnw.ip12.pipeitup.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.fhnw.ip12.pipeitup.app.ExcludeMethodFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.app.PipeItUp;
import ch.fhnw.ip12.pipeitup.data.Models.Edge;
import ch.fhnw.ip12.pipeitup.data.Models.GraphLayout;
import ch.fhnw.ip12.pipeitup.data.Models.HighscoreEntry;
import ch.fhnw.ip12.pipeitup.data.Models.Vertex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class DatabaseImpl implements Database {

	static final Logger log = LogManager.getLogger(PipeItUp.class.getName());

	private Connection connect() {
		String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/database.db";
		log.info("Loading Database: " + url);
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

		String sqlVertex =
			"SELECT id_vertex, positionX, positionY, LED, LEDLine FROM tbl_vertex LEFT JOIN tbl_vertexLED ON tbl_vertex.fk_vertexLED = tbl_vertexLED.id_vertexLED";
		String sqlGraphLayout =
			"SELECT firstVertex, secondVertex, firstLED, LEDLine, b1.mcp AS mcp1, b1.pin AS pin1, b2.mcp AS mcp2, b2.pin AS pin2 FROM tbl_graphlayout LEFT JOIN tbl_edgeLED ON tbl_graphlayout.fk_firstLED = tbl_edgeLED.id_edgeLED " +
				"LEFT JOIN tbl_button AS b1 ON tbl_graphlayout.fk_button1 = b1.id_button " +
				"LEFT JOIN tbl_button AS b2 ON tbl_graphlayout.fk_button2 = b2.id_button;";
		try (Connection conn = this.connect();
			 Statement stmtVertex = conn.createStatement();
			 ResultSet rsVertex = stmtVertex.executeQuery(sqlVertex);
			 Statement stmtGraphLayout = conn.createStatement();
			 ResultSet rsGraphLayout = stmtGraphLayout.executeQuery(sqlGraphLayout)) {
			while (rsVertex.next()) {
				vertexList.add(new Vertex(rsVertex.getInt("id_vertex"), rsVertex.getInt("positionX"),
					rsVertex.getInt("positionY"), rsVertex.getInt("LED"), rsVertex.getInt("LEDLine")));
			}
			while (rsGraphLayout.next()) {
				edgeList.add(
					new Edge(
						vertexList.get(rsGraphLayout.getInt("firstVertex")),
						vertexList.get(rsGraphLayout.getInt("secondVertex")),
						rsGraphLayout.getInt("firstLED"),
						rsGraphLayout.getInt("LEDLine"),
						rsGraphLayout.getInt("mcp1"),
						rsGraphLayout.getInt("pin1"),
						rsGraphLayout.getInt("mcp2"),
						rsGraphLayout.getInt("pin2")
					)
				);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}

		return new GraphLayout(new HashSet<>(vertexList), new HashSet<>(edgeList));
	}

	@Override
	@ExcludeMethodFromJacocoGeneratedReport
	public boolean saveScore(HighscoreEntry highscoreEntry) {
		String sql = "INSERT INTO tbl_highscore(name,score) VALUES(?,?)";
		try (Connection conn = this.connect();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, highscoreEntry.getUserName());
			pstmt.setInt(2, (int)(highscoreEntry.getScoreInSeconds() / 1000));
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return false;
	}

	@Override
	@ExcludeMethodFromJacocoGeneratedReport
	public List<HighscoreEntry> getScores() {
		ArrayList<HighscoreEntry> highcoreEntries = new ArrayList<>();
		String sql = "SELECT name, score FROM tbl_highscore";
		try (Connection conn = this.connect();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				highcoreEntries.add(new HighscoreEntry(rs.getString("name"), rs.getInt("score") / 1000f));
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}

		return highcoreEntries;
	}
}
