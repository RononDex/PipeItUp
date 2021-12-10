package ch.fhnw.ip12.pipeitup.ui;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoardViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchViewModel;

/**
* PipeItUpGameViewModel
*/
@ExcludeTypeFromJacocoGeneratedReport
public class PipeItUpGameViewModel {

	public TouchViewModel touchViewModel;
	public GameBoardViewModel gameBoardViewModel;

	public PipeItUpGameViewModel() {
		super();
		touchViewModel = new TouchViewModel();
		gameBoardViewModel = new GameBoardViewModel();
	}
	
}
