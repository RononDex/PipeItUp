package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class DeleteHighscoreController extends TouchUiController{

	@FXML
	Button yes, no;

	public void handleNo() throws Exception{
		viewModel.currentScene.setValue(TouchScene.SETTINGS);
	}

	public void handleYes() throws Exception{
		viewModel.deleteAllHighscoreEntries.setValue(true);
		viewModel.currentScene.setValue(TouchScene.SETTINGS);
	}

}
