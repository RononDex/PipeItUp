package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeMethodFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Timer;
import java.util.TimerTask;

@ExcludeTypeFromJacocoGeneratedReport
public class PreGameController extends TouchUiController {

	@Override
	public void initializeController() {
		this.gameboardViewModel.resetGameBoard.setValue(true);
		countdown.textProperty().bind(countDownDisplay);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			@ExcludeMethodFromJacocoGeneratedReport
			public void run() {
				startTimeSec--;
				boolean isSecondsZero = startTimeSec == 0;

				if (isSecondsZero) {
					Platform.runLater(new Runnable() {
						@Override
						@ExcludeMethodFromJacocoGeneratedReport
						public void run() {
							viewModel.currentScene.setValue(TouchScene.RUN);
						}
					});
				}

				Platform.runLater(new Runnable() {
					@Override
					@ExcludeMethodFromJacocoGeneratedReport
					public void run() {
						countDownDisplay.setValue(String.format("00:0%d", startTimeSec));
					}
				});
			}
		}, 0, 1000);

	}

	// private Timeline timeline = new Timeline();
	@FXML
	Label countdown;

	private int min;
	private int startTimeSec = 5;
	private StringProperty countDownDisplay = new SimpleStringProperty();
}
