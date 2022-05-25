package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeState;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class EM_PRIM5Controller extends TouchUiController{
	@FXML
	Button fArrow, bArrow;

	public void handleFArrow() throws Exception{
		viewModel.currentScene.setValue(TouchScene.EM_PRIM6);
	}
	public void handleBArrow() throws Exception{
		viewModel.currentScene.setValue(TouchScene.EM_PRIM4);
	}
	@Override
	public void initializeController() {
		//edge811
		gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 86 && e.vertex1.getVertexCenterPositionYInMm() == 281
						&& e.vertex2.getVertexCenterPositionXInMm() == 154 && e.vertex2.getVertexCenterPositionYInMm() == 398).
				findFirst().get().edgeState.setValue(EdgeState.SELECTED);
		gameboardViewModel.graphViewModel.getValue().vertexViewModels.stream().
				filter(v -> v.getVertexCenterPositionXInMm() == 154 && v.getVertexCenterPositionYInMm() == 398).
				findFirst().get().isLedActive.setValue(true);

		EdgeViewModel edge911 =gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 221 && e.vertex1.getVertexCenterPositionYInMm() == 281
						&& e.vertex2.getVertexCenterPositionXInMm() == 154 && e.vertex2.getVertexCenterPositionYInMm() == 398).
				findFirst().get();
		edge911.edgeState.setValue(EdgeState.BLINKING);

		EdgeViewModel edge1112 =gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 154 && e.vertex1.getVertexCenterPositionYInMm() == 398
						&& e.vertex2.getVertexCenterPositionXInMm() == 288 && e.vertex2.getVertexCenterPositionYInMm() == 398).
				findFirst().get();
		edge1112.edgeState.setValue(EdgeState.BLINKING);

		EdgeViewModel edge1113 =gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 154 && e.vertex1.getVertexCenterPositionYInMm() == 398
						&& e.vertex2.getVertexCenterPositionXInMm() == 86 && e.vertex2.getVertexCenterPositionYInMm() == 514).
				findFirst().get();
		edge1113.edgeState.setValue(EdgeState.BLINKING);


		EdgeViewModel edge1114 =gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 154 && e.vertex1.getVertexCenterPositionYInMm() == 398
						&& e.vertex2.getVertexCenterPositionXInMm() == 221 && e.vertex2.getVertexCenterPositionYInMm() == 514).
				findFirst().get();
		edge1114.edgeState.setValue(EdgeState.BLINKING);


		EdgeViewModel edge89 =gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 86 && e.vertex1.getVertexCenterPositionYInMm() == 281
						&& e.vertex2.getVertexCenterPositionXInMm() == 221 && e.vertex2.getVertexCenterPositionYInMm() == 281).
				findFirst().get();
		edge89.edgeState.setValue(EdgeState.BLINKING);

		gameboardViewModel.graphViewModel.getValue().vertexViewModels.stream().
				filter(v -> v.getVertexCenterPositionXInMm() == 221 && v.getVertexCenterPositionYInMm() == 281).
				findFirst().get().isLedActive.setValue(false);
	}
}
