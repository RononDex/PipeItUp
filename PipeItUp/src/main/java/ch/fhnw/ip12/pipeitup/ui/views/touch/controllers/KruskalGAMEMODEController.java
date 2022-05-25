package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;


import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameMode;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.MapMode;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class KruskalGAMEMODEController extends TouchUiController{

	@FXML
	Button backarrow, random, prebuilt, highscore;

	public void handleBackarrow() throws Exception{
		viewModel.currentScene.setValue(TouchScene.GAMEMODE_MENU);
	}

	public void handleRandom() throws Exception{
		gameboardViewModel.selectedMapMode.setValue(MapMode.RANDOM_WEIGHTS);
		viewModel.currentScene.setValue(TouchScene.PRIM_NAMEN_EINGEBEN);
	}

	public void handleHighscore() throws Exception{
		viewModel.currentScene.setValue(TouchScene.HIGHSCORE_PRIM);
	}

	@Override
	public void initializeController() {
		gameboardViewModel.gameMode.setValue(GameMode.KRUSKAL);
	}

}
