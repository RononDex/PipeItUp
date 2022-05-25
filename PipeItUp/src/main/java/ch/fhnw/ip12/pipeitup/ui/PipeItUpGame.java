package ch.fhnw.ip12.pipeitup.ui;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoard;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.hardware.HardwareGameBoardUi;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.software.SoftwareGameBoardUi;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchUiImpl;
import com.google.inject.Inject;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * PipeItUpUi - CONTROLLER
 *
 * Tasks: - initialize viewmodels - draw windows -
 */
@ExcludeTypeFromJacocoGeneratedReport
public class PipeItUpGame extends Application implements PipeItUpGameEntryPoint {

	static final Logger log = LogManager.getLogger(PipeItUpGame.class.getName());

	private static UiMode MODE = UiMode.HARDWARE;
	private static TouchUiImpl touch;
	private static GameBoard gameBoard;
	private static SoftwareGameBoardUi softwareGameBoard;
	private static HardwareGameBoardUi hardwareGameBoard;
	private static GameModeService viewModelServiceField;
	private static PipeItUpGameViewModel viewModel;

	public PipeItUpGame() {
		super();
	}

	@Inject
	public PipeItUpGame(SoftwareGameBoardUi softwareGameBoardUi, HardwareGameBoardUi hardwareGameBoardUi,
			TouchUiImpl touchUi, GameModeService viewModelService) {
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
		viewModel = viewModelServiceField.createStartUpViewModel();
		viewModel.touchViewModel.shutdownPipeItUp.addListener(listener -> stop());
		touch = new TouchUiImpl();
		boolean openTouchUiInFullScreen = true;

		if (MODE == UiMode.SOFTWARE) {
			gameBoard = softwareGameBoard;
			softwareGameBoard.setPrimaryStage(new Stage());
			openTouchUiInFullScreen = false;
		} else {
			gameBoard = hardwareGameBoard;
		}

		touch.setPrimaryStage(primaryStage);
		touch.setIsFullScreen(openTouchUiInFullScreen);
		touch.start(viewModel);
		gameBoard.setGameBoardViewModel(viewModel.gameBoardViewModel);
		gameBoard.start();
	}

	@Override
	public void stop() {
		log.info("Shutting down PipeItUp ...");
		if (viewModel.touchViewModel.shutdownPipeItUp.getValue()) {
			try {
				if (MODE == UiMode.HARDWARE) {
					hardwareGameBoard.stop();

					// Also shut down the device
					Runtime r = Runtime.getRuntime();
					log.info("Shutting down PC ...");
					r.exec("sudo shutdown now");

					viewModel.touchViewModel.shutdownPipeItUp.setValue(false);
				}
				if (MODE == UiMode.SOFTWARE) {
					softwareGameBoard.stop();
				}

				javafx.application.Platform.exit();
			} catch (Exception e) {
				log.fatal("Error while shutting down: " + e.getMessage());
			}
		}
	}
}
