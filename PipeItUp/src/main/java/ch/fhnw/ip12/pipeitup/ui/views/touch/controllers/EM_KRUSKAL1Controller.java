package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class EM_KRUSKAL1Controller extends TouchUiController{
	@FXML
	Button fArrow, bArrow;

	public void handleFArrow() throws Exception{
		viewModel.currentScene.setValue(TouchScene.EM_KRUSKAL2);
	}
	public void handleBArrow() throws Exception{
		viewModel.currentScene.setValue(TouchScene.EXPLAINMODE);
	}
	@Override
	public void initializeController(){
		gameboardViewModel.resetGameBoard.setValue(true);
	}
}
