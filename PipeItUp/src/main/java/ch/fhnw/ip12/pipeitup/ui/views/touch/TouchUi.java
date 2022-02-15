package ch.fhnw.ip12.pipeitup.ui.views.touch;

import ch.fhnw.ip12.pipeitup.ui.PipeItUpGameViewModel;
import javafx.stage.Stage;

/**
* TouchUi
*/
public interface TouchUi {

	void start(PipeItUpGameViewModel viewModel);
	void setPrimaryStage(Stage primaryStage);
    void setIsFullScreen(boolean isFullScreen);
	
}
