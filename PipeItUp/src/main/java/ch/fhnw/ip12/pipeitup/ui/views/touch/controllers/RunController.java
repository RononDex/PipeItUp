package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeMethodFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeState;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoardState;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

@ExcludeTypeFromJacocoGeneratedReport
public class RunController extends TouchUiController {

	@FXML
	Label run;

	@FXML
	Button cancel;

	private StringProperty timerDisplay = new SimpleStringProperty();

	Timer timer = new Timer();

	@Override
	public void initializeController() {
		this.gameboardViewModel.startNewGame.setValue(true);

		timer.schedule(new TimerTask() {
			@Override
			@ExcludeMethodFromJacocoGeneratedReport
			public void run() {

				float seconds = Duration
						.between(gameboardViewModel.gameStartMomentForHighscore.getValue(), LocalTime.now()).toMillis()
						/ 1000f;

				// Timer Fix When exceeding 99 seconds
				Platform.runLater(new Runnable() {
					@Override
					@ExcludeMethodFromJacocoGeneratedReport
					public void run() {
						run.textProperty().bindBidirectional(timerDisplay);
						if (seconds < 100) {
							timerDisplay.setValue(String.format("%3.1f", seconds));
						} else {
							timerDisplay.setValue(String.format("%3.0f", seconds));
						}
					}
				});

				// Punishment CSS Change
				Platform.runLater(new Runnable() {
					@Override
					@ExcludeMethodFromJacocoGeneratedReport
					public void run() {
						if (gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream()
								.anyMatch(e -> e.edgeState.getValue() == EdgeState.INVALID_SELECTION)) {
							run.setTextFill(Color.RED);
						} else {
							run.setTextFill(Color.BLUE);
						}
					}
				});

				// Game finish Screen change
				if (gameboardViewModel.gameBoardState.getValue() == GameBoardState.GAME_FINISHED) {
					Platform.runLater(new Runnable() {
						@Override
						@ExcludeMethodFromJacocoGeneratedReport
						public void run() {
							timer.cancel();
							viewModel.currentScene.setValue(TouchScene.END);
						}
					});
				}

			}
		}, 0, 100);

	}

	public void handleCancel() throws Exception {
		timer.cancel();
		this.gameboardViewModel.resetGameBoard.setValue(true);
		viewModel.currentScene.setValue(TouchScene.MAIN_MENU);
	}

}
