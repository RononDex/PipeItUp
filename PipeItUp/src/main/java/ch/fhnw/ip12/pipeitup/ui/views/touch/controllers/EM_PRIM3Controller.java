package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameMode;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.MapMode;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class EM_PRIM3Controller extends TouchUiController{
	@FXML
	Button fArrow, bArrow;

	public void handleFArrow() throws Exception{
		viewModel.currentScene.setValue(TouchScene.EM_PRIM4);
	}
	public void handleBArrow() throws Exception{
		viewModel.currentScene.setValue(TouchScene.EM_PRIM2);

	}
	@Override
	public void initializeController(){
		this.gameboardViewModel.resetGameBoard.setValue(true);
		this.gameboardViewModel.selectedMapMode.setValue(MapMode.RANDOM_WEIGHTS);
		this.gameboardViewModel.gameMode.setValue(GameMode.KRUSKAL);
		this.gameboardViewModel.startNewGame.setValue(true);
		gameboardViewModel.graphViewModel.getValue().vertexViewModels.stream().
				filter(v -> v.getVertexCenterPositionXInMm() == 86 && v.getVertexCenterPositionYInMm() == 281).
				findFirst().get().isLedActive.setValue(true);
		//811
		gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 86 && e.vertex1.getVertexCenterPositionYInMm() == 281
						&& e.vertex2.getVertexCenterPositionXInMm() == 154 && e.vertex2.getVertexCenterPositionYInMm() == 398).
				findFirst().get().weight.setValue(1);

		//89
		gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 86 && e.vertex1.getVertexCenterPositionYInMm() == 281
						&& e.vertex2.getVertexCenterPositionXInMm() == 221 && e.vertex2.getVertexCenterPositionYInMm() == 281).
				findFirst().get().weight.setValue(3);

//911
		gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 221 && e.vertex1.getVertexCenterPositionYInMm() == 281
						&& e.vertex2.getVertexCenterPositionXInMm() == 154 && e.vertex2.getVertexCenterPositionYInMm() == 398).
				findFirst().get().weight.setValue(2);
//1114
		gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 154 && e.vertex1.getVertexCenterPositionYInMm() == 398
						&& e.vertex2.getVertexCenterPositionXInMm() == 221 && e.vertex2.getVertexCenterPositionYInMm() == 514).
				findFirst().get().weight.setValue(1);
		//1113
		gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 154 && e.vertex1.getVertexCenterPositionYInMm() == 398
						&& e.vertex2.getVertexCenterPositionXInMm() == 86 && e.vertex2.getVertexCenterPositionYInMm() == 514).
				findFirst().get().weight.setValue(5);

		//1112
		gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().
				filter(e -> e.vertex1.getVertexCenterPositionXInMm() == 154 && e.vertex1.getVertexCenterPositionYInMm() == 398
						&& e.vertex2.getVertexCenterPositionXInMm() == 288 && e.vertex2.getVertexCenterPositionYInMm() == 398).
				findFirst().get().weight.setValue(4);
	}

}
