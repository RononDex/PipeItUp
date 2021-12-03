package ch.fhnw.ip12.pipeitup.ui;

import java.io.IOException;

import ch.fhnw.ip12.pipeitup.app.ExcludeMethodFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
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
@ExcludeTypeFromJacocoGeneratedReport
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
			@ExcludeMethodFromJacocoGeneratedReport
			public void run() {
				javafx.application.Application.launch(PipeItUpGame.class);
			}
		}.start();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		touchUi = new TouchUi();
		boolean openTouchUiInFullScreen = true;

		if (MODE == UiMode.SOFTWARE) {
			gameBoard = new SoftwareGameBoardUi(new Stage());
			openTouchUiInFullScreen = false;
		} else
			gameBoard = new HardwareGameBoard();

		touchUi.start(primaryStage, openTouchUiInFullScreen);
		gameBoard.start();
	}
}
