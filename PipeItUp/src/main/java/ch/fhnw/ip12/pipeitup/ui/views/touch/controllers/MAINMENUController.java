package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class MAINMENUController extends TouchUiController{

	@FXML
	Button gamemode, explainmode, settings;

	public void handleGamemode() throws Exception{
		viewModel.currentScene.setValue(TouchScene.GAMEMODE_MENU);
	}

	public void handleExplainmode() throws Exception{
		viewModel.currentScene.setValue(TouchScene.EXPLAINMODE);
	}

	public void handleSettings() throws Exception{
		viewModel.currentScene.setValue(TouchScene.SETTINGS);
	}


}
