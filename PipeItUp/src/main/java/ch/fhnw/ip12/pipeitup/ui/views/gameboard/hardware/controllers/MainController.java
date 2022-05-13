package ch.fhnw.ip12.pipeitup.ui.views.gameboard.hardware.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoardState;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoardViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameMode;

@ExcludeTypeFromJacocoGeneratedReport
public class MainController {
	private GameBoardViewModel gameBoardViewModel;

	public MainController(GameBoardViewModel gameBoardViewModel) {
		this.gameBoardViewModel = gameBoardViewModel;
	}

	public void initialize(){
		// TODO: buttonClickEvent

		// TODO: add all of the needed listeners
		gameBoardViewModel.selectedEdgeForValidation.addListener(observable -> updateGameBoardLEDs());
		gameBoardViewModel.graphViewModel.addListener(observable -> updateGameBoardLEDs());
		gameBoardViewModel.gameMode.addListener(observable ->  updateGameBoardLEDs());

		updateGameBoardLEDs();
		// TODO: Set game mode from menu
		gameBoardViewModel.gameMode.setValue(GameMode.KRUSKAL);
		gameBoardViewModel.gameBoardState.setValue(GameBoardState.SELECT_NEXT_EDGE);
	}

	private void updateGameBoardLEDs(){
		// TODO: update all the lights
		if (gameBoardViewModel == null || gameBoardViewModel.graphViewModel == null) {
			return;
		}

	}

}
