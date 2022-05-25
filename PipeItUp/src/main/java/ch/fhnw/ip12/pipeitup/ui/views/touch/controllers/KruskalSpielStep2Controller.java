package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;


import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class KruskalSpielStep2Controller extends TouchUiController{

	@FXML
	Button backarrow, newname, player;

	public void handleBackarrow() throws Exception{
		viewModel.currentScene.setValue(TouchScene.GAMEMODE_KRUSKAL);
	}

}
