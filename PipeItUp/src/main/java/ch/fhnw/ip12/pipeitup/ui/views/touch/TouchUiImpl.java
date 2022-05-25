package ch.fhnw.ip12.pipeitup.ui.views.touch;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.PipeItUpGameViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.touch.controllers.TouchUiController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.logging.Level;
import java.util.logging.Logger;

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
		this.isFullScreen = isFullScreen;
	}

@Override
	public void start(PipeItUpGameViewModel viewModel) {
		primaryStage.setTitle("Pipe-It-Up! Touchscreen");
		primaryStage.setHeight(480);
		primaryStage.setWidth(800);
		primaryStage.setFullScreen(isFullScreen);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		if (isFullScreen) {
			primaryStage.setResizable(false);
			primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		}
		updateScene(viewModel, true);

		primaryStage.setFullScreen(isFullScreen);
		primaryStage.show();

		viewModel.touchViewModel.currentScene.addListener(listener -> updateScene(viewModel, false));
	}

	private void updateScene(PipeItUpGameViewModel viewModel, boolean first) {
		try {
			Scene scene = getCurrentScene(viewModel);
			if (first) {
				primaryStage.setScene(scene);
				if (isFullScreen) {
					scene.setCursor(Cursor.NONE);
				}
			} else {
				Parent root = scene.getRoot();
				scene.setRoot(new GridPane());
				primaryStage.getScene().setRoot(root);
			}
		} catch (Exception ex) {
			log.log(Level.SEVERE, String.format("Failed to open touchUi: %s", ex.getMessage()));
		}
	}

	private Scene getCurrentScene(PipeItUpGameViewModel viewModel) {
		FXMLLoader fxmlLoader = new FXMLLoader();
		Scene scene = null;
		try {
			switch (viewModel.touchViewModel.currentScene.get()) {
				case START_SCREEN:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/StartScreen.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case MAIN_MENU:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/MAIN_MENU.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case GAMEMODE_MENU:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/GAMEMODE.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case EXPLAINMODE:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/EXPLAINMODE.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case GAMEMODE_PRIM:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/PRIM_GAMEMODE.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case GAMEMODE_KRUSKAL:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/KRUSKAL_GAMEMODE.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case KRUSKAL_SPIEL_STEP_2:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/KruskalSpielStep2.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case PRIM_SPIEL_STEP_2:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/PrimSpielStep2.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case PRIM_NAMEN_EINGEBEN:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/PrimNamenEingeben.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case PREGAMESCREEN:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/Pregame.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case RUN:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/Run.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case END:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/End.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case PAULINE1:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/Pauline1.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case PAULINE2:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/Pauline2.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case PAULINE3:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/Pauline3.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case PAULINE4:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/Pauline4.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case PAULINE5:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/Pauline5.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				default:
					throw new IllegalArgumentException("Unknown touch screen scene");
				case SPIELMECHANIK_K1:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/SPIELMECHANIK_K1.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case SPIELMECHANIK_K2:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/SPIELMECHANIK_K2.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case SPIELMECHANIK_K3:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/SPIELMECHANIK_K3.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case SPIELMECHANIK_K4:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/SPIELMECHANIK_K4.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case SPIELMECHANIK_K5:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/SPIELMECHANIK_K5.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case SPIELMECHANIK_K6:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/SPIELMECHANIK_K6.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case SPIELMECHANIK_K7:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/SPIELMECHANIK_K7.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case SPIELMECHANIK_P1:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/SPIELMECHANIK_P1.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case SPIELMECHANIK_P2:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/SPIELMECHANIK_P2.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case SPIELMECHANIK_P3:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/SPIELMECHANIK_P3.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case SPIELMECHANIK_P4:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/SPIELMECHANIK_P4.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case SPIELMECHANIK_P5:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/SPIELMECHANIK_P5.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case SPIELMECHANIK_P6:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/SPIELMECHANIK_P6.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case SPIELMECHANIK_P7:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/SPIELMECHANIK_P7.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case EM_KRUSKAL1:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/EM_KRUSKAL1.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case EM_KRUSKAL2:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/EM_KRUSKAL2.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case EM_KRUSKAL3:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/EM_KRUSKAL3.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case EM_KRUSKAL4:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/EM_KRUSKAL4.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case EM_KRUSKAL5:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/EM_KRUSKAL5.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case EM_PRIM1:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/EM_PRIM1.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case EM_PRIM2:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/EM_PRIM2.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case EM_PRIM3:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/EM_PRIM3.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case EM_PRIM4:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/EM_PRIM4.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case EM_PRIM5:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/EM_PRIM5.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case EM_PRIM6:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/EM_PRIM6.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case HIGHSCORE_PRIM:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/highscore.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case SETTINGS:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/settings.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case TURNOFF:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/turnoff.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
				case DELETE_HIGHSCORE:
					fxmlLoader.setLocation(getClass().getResource("/ui/views/touch/deletehighscore.fxml"));
					scene = new Scene(fxmlLoader.load(), SCREEN_WIDTH, SCREEN_HEIGHT);
					break;
			}
		} catch (Exception ex) {
			log.log(Level.SEVERE,
					String.format("Failed to load scene for %s: %s", viewModel.touchViewModel.currentScene.get(), ex.getMessage()));
		}

		if (scene != null) {
			TouchUiController controller = fxmlLoader.<TouchUiController>getController();
			controller.setViewModel(viewModel.touchViewModel);
			controller.setViewModel(viewModel.gameBoardViewModel);
			controller.initializeController();
		}

		return scene;
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
