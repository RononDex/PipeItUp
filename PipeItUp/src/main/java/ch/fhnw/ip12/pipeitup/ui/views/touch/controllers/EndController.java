package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeState;
import ch.fhnw.ip12.pipeitup.ui.views.touch.HighscoreEntryViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.time.Duration;

@ExcludeTypeFromJacocoGeneratedReport
public class EndController extends TouchUiController {

	@FXML
	Button repeat, end, highscore;

	@FXML
	Label endResult;
	@FXML
	Label kostenSum;

	private StringProperty resultDisplay = new SimpleStringProperty();

	@Override
	public void initializeController() {

		float seconds = Duration.between(gameboardViewModel.gameStartMomentForHighscore.getValue(),
				gameboardViewModel.gameEndMomentForHighscore.getValue()).toMillis() / 1000f;

		viewModel.newHighscoreEntryToSave.setValue(new HighscoreEntryViewModel(viewModel.userName.getValue(), seconds,
				gameboardViewModel.gameMode.getValue()));
		endResult.textProperty().bindBidirectional(resultDisplay);
		resultDisplay.setValue(String.format("%3.2f s", seconds));

		int sum = gameboardViewModel.graphViewModel.getValue().edgeViewModels.stream()
				.filter(e -> e.edgeState.getValue() == EdgeState.SELECTED).mapToInt(e -> e.weight.getValue()).sum();
		kostenSum.textProperty()
				.setValue("Gratulation, Pruskalistan hat wieder genug Energie.\r\n Deine totalen Kosten sind " + sum);
	}

	public void handleHighscore() throws Exception {
		this.gameboardViewModel.resetGameBoard.setValue(true);
		viewModel.currentScene.setValue(TouchScene.HIGHSCORE_PRIM);
	}

	public void handleRepeat() throws Exception {
		this.gameboardViewModel.resetGameBoard.setValue(true);
		viewModel.currentScene.setValue(TouchScene.PREGAMESCREEN);
	}

	public void handleEnd() throws Exception {
		this.gameboardViewModel.resetGameBoard.setValue(true);
		viewModel.currentScene.setValue(TouchScene.MAIN_MENU);
	}

}
