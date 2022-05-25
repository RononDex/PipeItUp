package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeState;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class EM_KRUSKAL5Controller extends TouchUiController {
	@FXML
	Button bArrow, home;

	public void handleBArrow() throws Exception {
		viewModel.currentScene.setValue(TouchScene.EM_KRUSKAL4);
	}

	public void handleHome() throws Exception {
		viewModel.currentScene.setValue(TouchScene.EXPLAINMODE);
	}

	@Override
	public void initializeController() {

		int nextMin = gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.edgeState.getValue() != EdgeState.SELECTED).map(e -> e.weight.getValue()).
				min(Integer::compareTo).get();

		EdgeViewModel nextVertex = gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.edgeState.getValue() != EdgeState.SELECTED && e.weight.getValue() == nextMin).findFirst().get();
		nextVertex.edgeState.setValue(EdgeState.SELECTED);

		nextVertex.vertex1.isLedActive.setValue(true);
		nextVertex.vertex2.isLedActive.setValue(true);
	}
}
