
package ch.fhnw.ip12.pipeitup.ui.views.gameboard.software;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoardViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.software.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SoftwareGameBoard
 */
@ExcludeTypeFromJacocoGeneratedReport
public class SoftwareGameBoardUiImpl implements SoftwareGameBoardUi {

	static Logger log = Logger.getLogger(SoftwareGameBoardUi.class.getName());

	private Stage primaryStage;

	private GameBoardViewModel gameBoardViewModel;

	@Override
	public void start() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/ui/views/gameboard/software/MainWindow.fxml"));
			fxmlLoader.setControllerFactory(aClass -> new MainController(gameBoardViewModel));
			Scene scene = new Scene(fxmlLoader.load());
			scene.getStylesheets()
					.add(getClass().getResource("/ui/views/gameboard/software/gameboard.css").toExternalForm());
			primaryStage.setTitle("Pipe-It-Up! Software GameBoard");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception ex) {
			log.log(Level.SEVERE, String.format("Failed to open softwareGameBoardUi: %s", ex.getMessage()));
		}
	}

	@Override
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public void setGameBoardViewModel(GameBoardViewModel gameBoardViewModel) {
		this.gameBoardViewModel = gameBoardViewModel;
	}

	@Override
	public void stop() { }
}
