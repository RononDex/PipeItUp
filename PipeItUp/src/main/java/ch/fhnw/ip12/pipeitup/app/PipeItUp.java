package ch.fhnw.ip12.pipeitup.app;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * PipeItUp
 */
public class PipeItUp extends Application {

	@ExcludeFromJacocoGeneratedReport
	public static void main(String[] args) {
		System.out.println("Pipe-It-Up is awesome!");

		launch(args);
	}

	@Override
	@ExcludeFromJacocoGeneratedReport
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root);

		//Setting the title to Stage. 
		primaryStage.setTitle("Sample application"); 
			
		//Setting the scene to Stage 
		primaryStage.setScene(scene); 
			
		//Displaying the stage 
		primaryStage.show();
	}

}
