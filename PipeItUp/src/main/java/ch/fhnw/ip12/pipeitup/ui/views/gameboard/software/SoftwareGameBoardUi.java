package ch.fhnw.ip12.pipeitup.ui.views.gameboard.software;

import java.util.logging.Level;
import java.util.logging.Logger;

import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoard;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * SoftwareGameBoard
 */
public class SoftwareGameBoardUi implements GameBoard {

    static Logger log = Logger.getLogger(SoftwareGameBoardUi.class.getName());

	private Stage primaryStage;

	public SoftwareGameBoardUi(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public void start() {
		try 
		{
        FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene);
        primaryStage.show();
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, String.format("Failed to open softwareGameBoardUi: %s", ex.getMessage()));
		}
	}

}
