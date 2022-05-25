package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class SPIELMECHANIK_K4Controller extends TouchUiController{
	@FXML
	Button bArrow, fArrow, skip;

	public void handleBArrow() throws Exception{
		viewModel.currentScene.setValue(TouchScene.SPIELMECHANIK_K3);
		this.gameboardViewModel.resetGameBoard.setValue(true);
	}
	public void handleFArrow() throws Exception{
		viewModel.currentScene.setValue(TouchScene.SPIELMECHANIK_K5);
	}
	public void handleSkip() throws Exception{
		viewModel.currentScene.setValue(TouchScene.EM_KRUSKAL1);
		this.gameboardViewModel.resetGameBoard.setValue(true);

	}
}
