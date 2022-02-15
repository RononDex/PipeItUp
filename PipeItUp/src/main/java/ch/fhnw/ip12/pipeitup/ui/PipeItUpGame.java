package ch.fhnw.ip12.pipeitup.ui;

import com.google.inject.Inject;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoard;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.hardware.HardwareGameBoardUi;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.software.SoftwareGameBoardUi;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchUiImpl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * PipeItUpUi - CONTROLLER
 *
 * Tasks: - initialize viewmodels - draw windows -
 */
@ExcludeTypeFromJacocoGeneratedReport
public class PipeItUpGame extends Application implements PipeItUpGameEntryPoint {

	private static UiMode MODE = UiMode.HARDWARE;
	private static TouchUiImpl touch;
	private static GameBoard gameBoard;
	private static SoftwareGameBoardUi softwareGameBoard;
	private static HardwareGameBoardUi hardwareGameBoard;
	private static ViewModelService viewModelServiceField;

	public PipeItUpGame() {
		super();
	}

	@Inject
	public PipeItUpGame(SoftwareGameBoardUi softwareGameBoardUi, HardwareGameBoardUi hardwareGameBoardUi,
			TouchUiImpl touchUi, ViewModelService viewModelService) {
		super();
		softwareGameBoard = softwareGameBoardUi;
		hardwareGameBoard = hardwareGameBoardUi;
		touch = touchUi;
		viewModelServiceField = viewModelService;
	}

	public void setUiMode(UiMode uiMode) {
		MODE = uiMode;
	}

	public void start() {
		new Thread(() -> javafx.application.Application.launch(PipeItUpGame.class)).start();
	}

	@Override
	public void start(Stage primaryStage) {
		PipeItUpGameViewModel initialViewModel = viewModelServiceField.createStartUpViewModel();
		touch = new TouchUiImpl();
		boolean openTouchUiInFullScreen = true;

		if (MODE == UiMode.SOFTWARE) {
			gameBoard = softwareGameBoard;
			softwareGameBoard.setPrimaryStage(new Stage());
			openTouchUiInFullScreen = false;
		} else
			gameBoard = hardwareGameBoard;

		touch.setPrimaryStage(primaryStage);
		touch.setIsFullScreen(openTouchUiInFullScreen);
		touch.start(initialViewModel);
		gameBoard.setGameBoardViewModel(initialViewModel.gameBoardViewModel);
		gameBoard.start();
	}
}
