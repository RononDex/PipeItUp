package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeState;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameMode;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.MapMode;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class EM_KRUSKAL3Controller extends TouchUiController{
	@FXML
	Button bArrow, fArrow;

	public void handleBArrow() throws Exception {
		viewModel.currentScene.setValue(TouchScene.EM_KRUSKAL2);
	}

	public void handleFArrow() throws Exception {
		viewModel.currentScene.setValue(TouchScene.EM_KRUSKAL4);
	}
	@Override
	public void initializeController() {
		this.gameboardViewModel.resetGameBoard.setValue(true);
		this.gameboardViewModel.selectedMapMode.setValue(MapMode.RANDOM_WEIGHTS);
		this.gameboardViewModel.gameMode.setValue(GameMode.KRUSKAL);
		this.gameboardViewModel.startNewGame.setValue(true);

		gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.weight.getValue() == 1).forEach(e -> e.edgeState.setValue(EdgeState.BLINKING));

	}
}
