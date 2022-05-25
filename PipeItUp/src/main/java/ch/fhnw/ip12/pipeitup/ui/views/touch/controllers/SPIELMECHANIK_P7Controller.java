package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeState;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class SPIELMECHANIK_P7Controller extends TouchUiController {
	@FXML
	Button bArrow, fArrow;

	public void handleBArrow() throws Exception {
		viewModel.currentScene.setValue(TouchScene.SPIELMECHANIK_P6);
	}

	public void handleFArrow() throws Exception {
		viewModel.currentScene.setValue(TouchScene.EM_PRIM1);
		this.gameboardViewModel.resetGameBoard.setValue(true);
	}
	@Override
	public void initializeController() {
		this.gameboardViewModel.resetGameBoard.setValue(true);
		gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 86 && e.vertex1.getVertexCenterPositionYInMm() == 514
						&& e.vertex2.getVertexCenterPositionXInMm() == 221 && e.vertex2.getVertexCenterPositionYInMm() == 514).
				findFirst().get().edgeState.setValue(EdgeState.SELECTED);
		gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 154 && e.vertex1.getVertexCenterPositionYInMm() == 398
						&& e.vertex2.getVertexCenterPositionXInMm() == 86 && e.vertex2.getVertexCenterPositionYInMm() == 514).
				findFirst().get().edgeState.setValue(EdgeState.SELECTED);

		gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 288 && e.vertex1.getVertexCenterPositionYInMm() == 398
						&& e.vertex2.getVertexCenterPositionXInMm() == 221 && e.vertex2.getVertexCenterPositionYInMm() == 514).
				findFirst().get().edgeState.setValue(EdgeState.INVALID_SELECTION);
		gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 154 && e.vertex1.getVertexCenterPositionYInMm() == 398
						&& e.vertex2.getVertexCenterPositionXInMm() == 288 && e.vertex2.getVertexCenterPositionYInMm() == 398).
				findFirst().get().edgeState.setValue(EdgeState.SELECTED);
		gameboardViewModel.graphViewModel.getValue().vertexViewModels.stream().
				filter(v -> v.getVertexCenterPositionXInMm() == 154 && v.getVertexCenterPositionYInMm() == 398).findFirst().get().isLedActive.setValue(true);
		gameboardViewModel.graphViewModel.getValue().vertexViewModels.stream().
				filter(v -> v.getVertexCenterPositionXInMm() == 86 && v.getVertexCenterPositionYInMm() == 514).findFirst().get().isLedActive.setValue(true);
		gameboardViewModel.graphViewModel.getValue().vertexViewModels.stream().
				filter(v -> v.getVertexCenterPositionXInMm() == 288 && v.getVertexCenterPositionYInMm() == 398).findFirst().get().isLedActive.setValue(true);
		gameboardViewModel.graphViewModel.getValue().vertexViewModels.stream().
				filter(v -> v.getVertexCenterPositionXInMm() == 221 && v.getVertexCenterPositionYInMm() == 514).findFirst().get().isLedActive.setValue(true);


	}
}
