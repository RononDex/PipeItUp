package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class GAMEMODEController extends TouchUiController{

	@FXML
	Button primGM, kruskalGM, backarrow;

	public void handlePrimGM() throws Exception{
		viewModel.currentScene.setValue(TouchScene.GAMEMODE_PRIM);
	}

	public void handleBackarrow() throws Exception{
		viewModel.currentScene.setValue(TouchScene.MAIN_MENU);
	}

	public void handleKruskalGM() throws Exception{
		viewModel.currentScene.setValue(TouchScene.GAMEMODE_KRUSKAL);
	}

}
