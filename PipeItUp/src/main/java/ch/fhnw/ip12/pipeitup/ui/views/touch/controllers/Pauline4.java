package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class Pauline4 extends TouchUiController{

	@FXML
	Button back, forward, skip;

	public void handleBack() throws Exception{
		viewModel.currentScene.setValue(TouchScene.PAULINE3);
	}

	public void handleForward() throws Exception{
		viewModel.currentScene.setValue(TouchScene.PAULINE5);
	}



}
