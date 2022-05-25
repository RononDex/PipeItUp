package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoardViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchViewModel;

/**
* TouchUiController
*/
@ExcludeTypeFromJacocoGeneratedReport
public abstract class TouchUiController {

	protected TouchViewModel viewModel;
	protected GameBoardViewModel gameboardViewModel;

	public void setViewModel(TouchViewModel viewModel) {
		this.viewModel = viewModel;
	}

	public void setViewModel(GameBoardViewModel viewModel) {
		this.gameboardViewModel = viewModel;
	}

	public void initializeController() {}
	
}
