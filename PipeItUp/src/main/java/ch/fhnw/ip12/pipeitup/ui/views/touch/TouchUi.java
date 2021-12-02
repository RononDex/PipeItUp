package ch.fhnw.ip12.pipeitup.ui.views.touch;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
* TouchStage
*/
public class TouchUi {

	 Stage primaryStage;

	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene);
        primaryStage.show();
		
	}
	
}
