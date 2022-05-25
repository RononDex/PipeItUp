package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeState;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class SPIELMECHANIK_K5Controller extends TouchUiController {
	@FXML
	Button bArrow, fArrow, skip;

	public void handleBArrow() throws Exception {
		viewModel.currentScene.setValue(TouchScene.SPIELMECHANIK_K4);
		this.gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().forEach(e -> e.edgeState.setValue(EdgeState.DEACTIVATED));
		this.gameboardViewModel.graphViewModel.getValue().vertexViewModels.stream().forEach(v -> v.isLedActive.setValue(true));
	}

	public void handleFArrow() throws Exception {
		viewModel.currentScene.setValue(TouchScene.SPIELMECHANIK_K6);
	}

	public void handleSkip() throws Exception {
		viewModel.currentScene.setValue(TouchScene.EM_KRUSKAL1);
		this.gameboardViewModel.resetGameBoard.setValue(true);
	}

	@Override
	public void initializeController() {
		gameboardViewModel.showSolution.setValue(true);
		this.gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().forEach(e->e.weight.setValue(6));

	}
}
