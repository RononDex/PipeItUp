package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeState;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class EM_KRUSKAL4Controller extends TouchUiController{
	@FXML
	Button bArrow, fArrow;

	public void handleBArrow() throws Exception {
		viewModel.currentScene.setValue(TouchScene.EM_KRUSKAL3);
	}

	public void handleFArrow() throws Exception {
		viewModel.currentScene.setValue(TouchScene.EM_KRUSKAL5);
	}
	@Override
	public void initializeController() {
		gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				forEach(e -> e.vertex1.isLedActive.setValue(false));

		gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				forEach(e -> e.vertex2.isLedActive.setValue(false));

		EdgeViewModel firstE = gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.weight.getValue() == 1).findFirst().get();
		firstE.edgeState.setValue(EdgeState.SELECTED);
		firstE.vertex1.isLedActive.setValue(true);
		firstE.vertex2.isLedActive.setValue(true);


		int minimumWeightNotBlinking = gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.edgeState.getValue() != EdgeState.SELECTED).map(e -> e.weight.getValue()).
				min(Integer::compareTo).get();
		gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.weight.getValue() == minimumWeightNotBlinking && e != firstE).forEach(e->e.edgeState.setValue(EdgeState.BLINKING));

		gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.weight.getValue() == 1 && e.edgeState.equals(EdgeState.UNSELECTED)).
				forEach(e -> e.edgeState.setValue(EdgeState.BLINKING));

	}
}
