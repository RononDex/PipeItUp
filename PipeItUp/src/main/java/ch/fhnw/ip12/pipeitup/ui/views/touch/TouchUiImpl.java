package ch.fhnw.ip12.pipeitup.ui.views.touch;

import java.util.logging.Level;
import java.util.logging.Logger;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.PipeItUpGameViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * TouchStage
 */
@ExcludeTypeFromJacocoGeneratedReport
public class TouchUiImpl implements TouchUi {

	static Logger log = Logger.getLogger(TouchUiImpl.class.getName());

	private static final int SCREEN_WIDTH = 800;
	private static final int SCREEN_HEIGHT = 480;

	private Stage primaryStage;
	private boolean isFullScreen;

	public void start(boolean isFullScreen) {

	}

	@Override
	public void start(PipeItUpGameViewModel viewModel) {
		try {
			Scene scene = getCurrentScene(viewModel.touchViewModel);
			primaryStage.setTitle("Pipe-It-Up! Touchscreen");
			primaryStage.setScene(scene);
			primaryStage.setHeight(480);
			primaryStage.setWidth(800);
			primaryStage.setFullScreen(isFullScreen);
			primaryStage.show();
		} catch (Exception ex) {
			log.log(Level.SEVERE, String.format("Failed to open touchUi: %s", ex.getMessage()));
		}
	}

	private Scene getCurrentScene(TouchViewModel viewModel) {
		FXMLLoader fxmlLoader = new FXMLLoader();
		try {
			switch (viewModel.currentScene.get()) {
				case START_SCREEN:
					fxmlLoader.setLocation(getClass().getResource("StartScreen.fxml"));
					Scene scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					scene.getStylesheets().add(getClass().getResource("touch.css").toExternalForm());
					return scene;

				default:
					throw new IllegalArgumentException("Unknown touch screen scene");
			}
		} catch (Exception ex) {
			log.log(Level.SEVERE,
					String.format("Failed to load scene for %s: %s", viewModel.currentScene.get(), ex.getMessage()));
		}
		return null;
	}

	@Override
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;

	}

	@Override
	public void setIsFullScreen(boolean isFullScreen) {
		this.isFullScreen = isFullScreen;
	}

}
