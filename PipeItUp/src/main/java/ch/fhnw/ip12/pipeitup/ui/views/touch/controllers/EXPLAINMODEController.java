package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;


import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeState;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@ExcludeTypeFromJacocoGeneratedReport
public class EXPLAINMODEController extends TouchUiController{

	@FXML
	Button primEM, kruskalEM, backarrow;

	public void handlePrimEM() throws Exception{
		viewModel.currentScene.setValue(TouchScene.SPIELMECHANIK_P1);
		this.gameboardViewModel.graphViewModel.getValue().vertexViewModels.stream().forEach(v-> v.isLedActive.setValue(true));//blau
		this.gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().forEach(e->e.edgeState.setValue(EdgeState.UNSELECTED));
		this.gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().forEach(e->e.weight.setValue(6));

	}

	public void handleKruskalEM() throws Exception{
		viewModel.currentScene.setValue(TouchScene.SPIELMECHANIK_K1);
		this.gameboardViewModel.graphViewModel.getValue().vertexViewModels.stream().forEach(v-> v.isLedActive.setValue(true)); //blau
		this.gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().forEach(e->e.edgeState.setValue(EdgeState.UNSELECTED));
		this.gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream().forEach(e->e.weight.setValue(6));

	}

	public void handleBackarrow() throws Exception{
		viewModel.currentScene.setValue(TouchScene.MAIN_MENU);
		this.gameboardViewModel.resetGameBoard.setValue(true);
	}
	@Override
	public void initializeController(){
		gameboardViewModel.resetGameBoard.setValue(true);
	}

}
