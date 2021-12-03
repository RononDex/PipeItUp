package ch.fhnw.ip12.pipeitup.ui.views.touch;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * TouchStage
 */
@ExcludeTypeFromJacocoGeneratedReport
public class TouchUi {

	static Logger log = Logger.getLogger(TouchUi.class.getName());

	Stage primaryStage;

	public void start(Stage primaryStage, boolean isFullScreen) {
		try {
			this.primaryStage = primaryStage;
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("MainWindow.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 320, 240);
			scene.getStylesheets().add(getClass().getResource("touch.css").toExternalForm());
			primaryStage.setTitle("Pipe-It-Up! Touchscreen");
			primaryStage.setScene(scene);
			primaryStage.setHeight(480);
			primaryStage.setWidth(800);
			primaryStage.setFullScreen(isFullScreen);
			primaryStage.show();
		} catch (Exception ex) {
			log.log(Level.SEVERE, String.format("Failed to open softwareGameBoardUi: %s", ex.getMessage()));
		}

	}

}
