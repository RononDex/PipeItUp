package ch.fhnw.ip12.pipeitup.ui.views.gameboard.software;

import org.junit.Test;

import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoardViewModel;

/**
* SoftwareGameBoard
*/
public class SoftwareGameBoardTests {

	@Test
	public void Test()
	{
		SoftwareGameBoard gameBoard = new SoftwareGameBoard();
		GameBoardViewModel viewModel = gameBoard.viewModel;
		gameBoard.start();
	}
	
}
