package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeState;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class SPIELMECHANIK_K2Controller extends TouchUiController {
	@FXML
	Button fArrow, bArrow, skip;

	public void handleFArrow() throws Exception{
		viewModel.currentScene.setValue(TouchScene.SPIELMECHANIK_K3);

	}
	public void handlebArrow() throws Exception {
		viewModel.currentScene.setValue(TouchScene.SPIELMECHANIK_K1);
		this.gameboardViewModel.graphViewModel.getValue().vertexViewModels.stream().forEach(v-> v.isLedActive.setValue(true));
		this.gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().forEach(e->e.edgeState.setValue(EdgeState.UNSELECTED));
		this.gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().forEach(e->e.weight.setValue(6));

	}

	public void handleskip() throws Exception {
		viewModel.currentScene.setValue(TouchScene.EM_KRUSKAL1);
		this.gameboardViewModel.resetGameBoard.setValue(true);

	}
}
