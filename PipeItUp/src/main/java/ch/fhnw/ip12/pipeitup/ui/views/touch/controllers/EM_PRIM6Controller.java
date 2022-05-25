package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeState;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class EM_PRIM6Controller extends TouchUiController{
	@FXML
	Button home, bArrow;

	public void handleHome() throws Exception{
		viewModel.currentScene.setValue(TouchScene.EXPLAINMODE);
	}
	public void handleBArrow() throws Exception{
		viewModel.currentScene.setValue(TouchScene.EM_PRIM5);
	}
	@Override
	public void initializeController() {
		EdgeViewModel edge811 = gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 86 && e.vertex1.getVertexCenterPositionYInMm() == 281
						&& e.vertex2.getVertexCenterPositionXInMm() == 154 && e.vertex2.getVertexCenterPositionYInMm() == 398).
				findFirst().get();
		edge811.edgeState.setValue(EdgeState.SELECTED);
		EdgeViewModel edge911 =gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 221 && e.vertex1.getVertexCenterPositionYInMm() == 281
						&& e.vertex2.getVertexCenterPositionXInMm() == 154 && e.vertex2.getVertexCenterPositionYInMm() == 398).
				findFirst().get();
		edge911.edgeState.setValue(EdgeState.UNSELECTED);

		EdgeViewModel edge1113 =gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 154 && e.vertex1.getVertexCenterPositionYInMm() == 398
						&& e.vertex2.getVertexCenterPositionXInMm() == 86 && e.vertex2.getVertexCenterPositionYInMm() == 514).
				findFirst().get();
		edge1113.edgeState.setValue(EdgeState.UNSELECTED);

		EdgeViewModel edge1112 =gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 154 && e.vertex1.getVertexCenterPositionYInMm() == 398
						&& e.vertex2.getVertexCenterPositionXInMm() == 288 && e.vertex2.getVertexCenterPositionYInMm() == 398).
				findFirst().get();
		edge1112.edgeState.setValue(EdgeState.UNSELECTED);

		EdgeViewModel edge1114 =gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 154 && e.vertex1.getVertexCenterPositionYInMm() == 398
						&& e.vertex2.getVertexCenterPositionXInMm() == 221 && e.vertex2.getVertexCenterPositionYInMm() == 514).
				findFirst().get();
		edge1114.edgeState.setValue(EdgeState.SELECTED);

		EdgeViewModel edge89 =gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 86 && e.vertex1.getVertexCenterPositionYInMm() == 281
						&& e.vertex2.getVertexCenterPositionXInMm() == 221 && e.vertex2.getVertexCenterPositionYInMm() == 281).
				findFirst().get();
		edge89.edgeState.setValue(EdgeState.UNSELECTED);

		gameboardViewModel.graphViewModel.getValue().vertexViewModels.stream().
				filter(v -> v.getVertexCenterPositionXInMm() == 221 && v.getVertexCenterPositionYInMm() == 514).
				findFirst().get().isLedActive.setValue(true);

	}

}
