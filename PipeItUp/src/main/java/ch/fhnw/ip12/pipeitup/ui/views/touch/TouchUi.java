package ch.fhnw.ip12.pipeitup.ui.views.touch;

import javafx.stage.Stage;

/**
* TouchUi
*/
public interface TouchUi {

	void start();
	void setPrimaryStage(Stage primaryStage);
    void setIsFullScreen(boolean isFullScreen);
	
}
