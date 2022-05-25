
package ch.fhnw.ip12.pipeitup.ui.views.gameboard.hardware;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.app.PipeItUp;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoardViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.hardware.controllers.MainController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * HardwareGameBoard
 */
@ExcludeTypeFromJacocoGeneratedReport
public class HardwareGameBoardUiImpl implements HardwareGameBoardUi {

	static final Logger log = LogManager.getLogger(PipeItUp.class.getName());
	private GameBoardViewModel gameBoardViewModel;
	private MainController mainController;

	@Override
	public void start() {
		mainController = new MainController(gameBoardViewModel);
		mainController.initialize();
	}

	@Override
	public void setGameBoardViewModel(GameBoardViewModel gameBoardViewModel) {
		this.gameBoardViewModel = gameBoardViewModel;
	}

	@Override
	public void stop() {
		mainController.clearLEDLines();
		mainController.renderLEDLines();
		mainController.buttonListener.interrupt();
	}
}
