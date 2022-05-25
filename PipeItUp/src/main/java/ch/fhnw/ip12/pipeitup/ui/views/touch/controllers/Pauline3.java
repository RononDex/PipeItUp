package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class Pauline3 extends TouchUiController{

	@FXML
	Button back, forward, skip;

	public void handleBack() throws Exception{
		viewModel.currentScene.setValue(TouchScene.PAULINE2);
	}

	public void handleForward() throws Exception{
		viewModel.currentScene.setValue(TouchScene.PAULINE4);
	}

	public void handleSkip() throws Exception{
		viewModel.currentScene.setValue(TouchScene.PREGAMESCREEN);
	}

}
