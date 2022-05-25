package ch.fhnw.ip12.pipeitup.ui.views.touch.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.touch.HighscoreEntryViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

@ExcludeTypeFromJacocoGeneratedReport
public class HSPrimController extends TouchUiController {

	@FXML
	Button back;

	@FXML
	TableView<HighscoreEntryViewModel> results;

	@FXML
	TableColumn<HighscoreEntryViewModel, String> name;

	@FXML
	TableColumn<HighscoreEntryViewModel, Float> time;

	@FXML
	TableColumn<HighscoreEntryViewModel, String> gamemode;




	public void handleBack() throws Exception{
		viewModel.currentScene.setValue(TouchScene.GAMEMODE_MENU);
	}

	@Override
	public void initializeController(){



		time.setSortable(true);
		name.setSortable(false);



		name.setCellValueFactory(cellData -> cellData.getValue().userName);
		time.setCellValueFactory(cellData -> cellData.getValue().highscoreInSeconds.asObject());
		// time.setCellValueFactory(cellData -> Bindings.format("%.2f", cellData.getValue().highscoreInSeconds));
		gamemode.setCellValueFactory(cellData -> Bindings.format("%s", cellData.getValue().gameMode));

		results.setItems(viewModel.highscoreEntries);
		results.getSortOrder().add(time);






	}







}
