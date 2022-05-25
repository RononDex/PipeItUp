package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class SPIELMECHANIK_K6Controller extends TouchUiController {
	@FXML
	Button bArrow, fArrow, skip;

	public void handleBArrow() throws Exception {
		viewModel.currentScene.setValue(TouchScene.SPIELMECHANIK_K5);
	}

	public void handleFArrow() throws Exception {
		viewModel.currentScene.setValue(TouchScene.SPIELMECHANIK_K7);
	}

	public void handleSkip() throws Exception {
		viewModel.currentScene.setValue(TouchScene.EM_KRUSKAL1);
		this.gameboardViewModel.resetGameBoard.setValue(true);
	}
	@Override
	public void initializeController() {
		this.gameboardViewModel.resetGameBoard.setValue(true);
		this.gameboardViewModel.graphViewModel.getValue().vertexViewModels.stream().forEach(v-> v.isLedActive.setValue(true));
		gameboardViewModel.showSolution.setValue(true);
	}
}
