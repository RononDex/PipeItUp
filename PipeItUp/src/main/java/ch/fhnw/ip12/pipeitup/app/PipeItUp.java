package ch.fhnw.ip12.pipeitup.app;

import java.util.Arrays;
import java.util.List;

import ch.fhnw.ip12.pipeitup.ui.PipeItUpGame;
import ch.fhnw.ip12.pipeitup.ui.UiMode;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoard;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.hardware.HardwareGameBoard;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.software.SoftwareGameBoard;

/**
 * PipeItUp
 */
public class PipeItUp {

	@ExcludeFromJacocoGeneratedReport
	public static void main(String[] args) {
		
		List<String> argsList = Arrays.asList(args);
		UiMode uiMode = UiMode.HARDWARE;

		if (argsList.contains("--softwareUi"))
			uiMode = UiMode.SOFTWARE;

		PipeItUpGame game = new PipeItUpGame(uiMode);

		game.start();
	}
}
