package ch.fhnw.ip12.pipeitup.ui.views.gameboard.software.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeState;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoardState;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoardViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameMode;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.VertexViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.software.Vector2;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * MainController
 */
@ExcludeTypeFromJacocoGeneratedReport
public class MainController {

	private GameBoardViewModel gameBoardViewModel;
	private static final int VERTEX_RADIUS = 20;
	private double uiScale = 1;

	public MainController(GameBoardViewModel gameBoardViewModel) {
		this.gameBoardViewModel = gameBoardViewModel;
	}

	public void initialize() {
		uiScale = gameBoardCanvas.getWidth() / 460;
		gameBoardCanvas.setHeight(660 * uiScale);
		gameBoardCanvas.setOnMouseClicked(event -> onMouseClick(event));
		gameBoardViewModel.selectedEdgeForValidation.addListener(listener -> updateGameBoardCanvas());
		gameBoardViewModel.graphViewModel.addListener(listener -> updateGameBoardCanvas());

		updateGameBoardCanvas();

		// TODO: Set game mode from menu
		gameBoardViewModel.gameMode.setValue(GameMode.KRUKSAL);
		gameBoardViewModel.gameBoardState.setValue(GameBoardState.SELECT_NEXT_EDGE);
	}

	@FXML
	private Canvas gameBoardCanvas;

	private void updateGameBoardCanvas() {
		GraphicsContext gc = gameBoardCanvas.getGraphicsContext2D();
		if (gameBoardViewModel == null || gameBoardViewModel.graphViewModel == null) {
			return;
		}

		gc.clearRect(0, 0, gameBoardCanvas.getWidth(), gameBoardCanvas.getHeight());

		// Draw edges
		for (EdgeViewModel edge : gameBoardViewModel.graphViewModel.getValue().edgeViewModels) {
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(3.0);
			if (edge.edgeState.get() == EdgeState.SELECTED) {
				gc.setStroke(Color.GREEN);
			}
			if (edge.edgeState.get() == EdgeState.INVALID_SELECTION) {
				gc.setStroke(Color.RED);
			}
			gc.strokeLine(edge.vertex1.getVertexCenterPositionXInMm() * uiScale,
					edge.vertex1.getVertexCenterPositionYInMm() * uiScale,
					edge.vertex2.getVertexCenterPositionXInMm() * uiScale,
					edge.vertex2.getVertexCenterPositionYInMm() * uiScale);

			gc.setLineWidth(1.0);
			Vector2 v1 = new Vector2(edge.vertex1.getVertexCenterPositionXInMm() * uiScale,
					edge.vertex1.getVertexCenterPositionYInMm() * uiScale);
			Vector2 v2 = new Vector2(edge.vertex2.getVertexCenterPositionXInMm() * uiScale,
					edge.vertex2.getVertexCenterPositionYInMm() * uiScale);
			Vector2 middleOfLine = v1.subtract(v2).divide(2).add(v2).add(new Vector2(10 * uiScale, -5 * uiScale));

			gc.strokeText(Integer.toString(edge.weight.get()), middleOfLine.x, middleOfLine.y);
		}

		gc.setLineWidth(1.0);

		// Draw Vertices
		double scaledVertexRadius = VERTEX_RADIUS * uiScale;
		for (VertexViewModel vertex : gameBoardViewModel.graphViewModel.getValue().vertexViewModels) {

			gc.setFill(Color.DARKGRAY);
			gc.fillOval(vertex.getVertexCenterPositionXInMm() * uiScale - scaledVertexRadius,
					vertex.getVertexCenterPositionYInMm() * uiScale - scaledVertexRadius,
					2 * scaledVertexRadius, 2 * scaledVertexRadius);
		}
	}

	private void onMouseClick(MouseEvent event) {
		Vector2 clickCoordinates = new Vector2(event.getX(), event.getY());

		for (VertexViewModel vertex : gameBoardViewModel.graphViewModel.getValue().vertexViewModels) {
			if (clickCoordinates.distance(
					new Vector2(
							vertex.getVertexCenterPositionXInMm(),
							vertex.getVertexCenterPositionYInMm())) <= VERTEX_RADIUS) {
				// vertex was clicked
			}
		}

		if (gameBoardViewModel.gameBoardState.getValue() == GameBoardState.SELECT_NEXT_EDGE) {

			for (EdgeViewModel edge : gameBoardViewModel.graphViewModel.getValue().edgeViewModels) {
				if (clickedOnEdge(edge, clickCoordinates)) {
					// edge clicked
					System.out.println(
							"Edge clicked connecting vertices " + edge.vertex1.getVertexNumber() + " and "
									+ edge.vertex2.getVertexNumber());

					gameBoardViewModel.selectedEdgeForValidation.setValue(edge);
				}
			}
		}
	}

	private boolean clickedOnEdge(EdgeViewModel edge, Vector2 p) {
		double lineThicknessForHitBox = 20 * uiScale;
		Vector2 v1 = new Vector2(
				edge.vertex1.getVertexCenterPositionXInMm() * uiScale,
				edge.vertex1.getVertexCenterPositionYInMm() * uiScale);
		Vector2 v2 = new Vector2(
				edge.vertex2.getVertexCenterPositionXInMm() * uiScale,
				edge.vertex2.getVertexCenterPositionYInMm() * uiScale);

		Vector2 lineVector = v1.subtract(v2);

		Vector2 rotatedVector = lineVector.rotate(Math.PI / 2).normalize().multiply(lineThicknessForHitBox / 2);
		Vector2 A = v1.add(rotatedVector);
		Vector2 B = v2.add(rotatedVector);
		Vector2 C = v1.subtract(rotatedVector);
		Vector2 D = v2.subtract(rotatedVector);

		return pointInRectangle(A, B, C, D, p);
	}

	boolean pointInTriangle(Vector2 A, Vector2 B, Vector2 C, Vector2 P) {
		// Compute vectors
		Vector2 v0 = C.subtract(A);
		Vector2 v1 = B.subtract(A);
		Vector2 v2 = P.subtract(A);

		// Compute dot products
		double dot00 = Vector2.dot(v0, v0);
		double dot01 = Vector2.dot(v0, v1);
		double dot02 = Vector2.dot(v0, v2);
		double dot11 = Vector2.dot(v1, v1);
		double dot12 = Vector2.dot(v1, v2);

		// Compute barycentric coordinates
		double invDenom = 1 / (dot00 * dot11 - dot01 * dot01);
		double u = (dot11 * dot02 - dot01 * dot12) * invDenom;
		double v = (dot00 * dot12 - dot01 * dot02) * invDenom;

		// Check if point is in triangle
		if (u >= 0 && v >= 0 && (u + v) < 1) {
			return true;
		} else {
			return false;
		}
	}

	boolean pointInRectangle(Vector2 X, Vector2 Y, Vector2 Z, Vector2 W, Vector2 P) {
		if (pointInTriangle(X, Y, Z, P))
			return true;
		if (pointInTriangle(X, Z, W, P))
			return true;
		return false;
	}

}
