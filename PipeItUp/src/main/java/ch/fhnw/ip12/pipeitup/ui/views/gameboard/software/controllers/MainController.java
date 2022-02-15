package ch.fhnw.ip12.pipeitup.ui.views.gameboard.software.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoardViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.VertexViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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

		updateGameBoardCanvas();
	}

	@FXML
	private Canvas gameBoardCanvas;

	private void updateGameBoardCanvas() {
		GraphicsContext gc = gameBoardCanvas.getGraphicsContext2D();
		if (gameBoardViewModel == null || gameBoardViewModel.getGraphViewModel() == null) {
			return;
		}

		for (EdgeViewModel edge : gameBoardViewModel.getGraphViewModel().getValue().getEdgeViewModels().getValue()) {
			gc.setStroke(Color.BLACK);
			gc.strokeLine(edge.vertex1.getVertexCenterPositionXInMm().getValue() * uiScale,
					edge.vertex1.getVertexCenterPositionYInMm().getValue() * uiScale,
					edge.vertex2.getVertexCenterPositionXInMm().getValue() * uiScale,
					edge.vertex2.getVertexCenterPositionYInMm().getValue() * uiScale);
		}

		double scaledVertexRadius = VERTEX_RADIUS * uiScale;
		for (VertexViewModel vertex : gameBoardViewModel.getGraphViewModel().getValue().getVertexViewModels()
				.getValue()) {

			gc.setFill(Color.DARKGRAY);
			gc.fillOval(vertex.getVertexCenterPositionXInMm().getValue() * uiScale - scaledVertexRadius,
					vertex.getVertexCenterPositionYInMm().getValue() * uiScale - scaledVertexRadius,
					2 * scaledVertexRadius, 2 * scaledVertexRadius);
			gc.strokeText(vertex.getVertexNumber().getValue().toString(),
					vertex.getVertexCenterPositionXInMm().getValue() * uiScale,
					vertex.getVertexCenterPositionYInMm().getValue() * uiScale);

		}
	}

}
