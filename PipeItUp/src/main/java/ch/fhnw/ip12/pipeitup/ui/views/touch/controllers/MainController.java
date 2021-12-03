package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.ui.PipeItUpGameViewModel;
import javafx.scene.input.TouchPoint;

/**
* MainCntroller
*/
public class MainController {

	TouchViewModel touchViewModel;

	public MainController(PipeItUpGameViewModel viewModel) {
		touchViewModel = viewModel.touchViewModel;
	}
	
}
