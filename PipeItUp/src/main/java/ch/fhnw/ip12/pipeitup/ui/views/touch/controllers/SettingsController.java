package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class SettingsController extends TouchUiController{

	@FXML
	Button back, highscore, turnoff;

	public void handleBack() throws Exception{
		viewModel.currentScene.setValue(TouchScene.MAIN_MENU);
	}

	public void handleHighscore() throws Exception{
		viewModel.currentScene.setValue(TouchScene.DELETE_HIGHSCORE);
	}

	public void handleTurnoff() throws Exception{
		viewModel.currentScene.setValue(TouchScene.TURNOFF);
	}

}
