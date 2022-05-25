package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class Pauline5 extends TouchUiController{

	@FXML
	Button start;

	public void handleStart() throws Exception{
		viewModel.currentScene.setValue(TouchScene.PREGAMESCREEN);
	}

}
