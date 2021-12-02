package ch.fhnw.ip12.pipeitup.ui;

import java.io.IOException;

import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoard;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.hardware.HardwareGameBoard;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.software.SoftwareGameBoardUi;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchUi;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * PipeItUpUi - CONTROLLER
 *
 * Tasks: - initialize viewmodels - draw windows -
 */
public class PipeItUpGame extends Application {

	private static UiMode MODE = UiMode.HARDWARE;
	private TouchUi touchUi;
	private GameBoard gameBoard;



	public PipeItUpGame() {
	}

	public PipeItUpGame(UiMode mode) {
		MODE = mode;
	}

	public void start() throws IOException {
		new Thread() {
			@Override
			public void run() {
				javafx.application.Application.launch(PipeItUpGame.class);
			}
		}.start();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		touchUi = new TouchUi();
		touchUi.start(primaryStage);		

		if (MODE == UiMode.SOFTWARE)
			gameBoard = new SoftwareGameBoardUi(new Stage());
		else
			gameBoard = new HardwareGameBoard();

		gameBoard.start();
	}
}
