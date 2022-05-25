package ch.fhnw.ip12.pipeitup.ui.views.touch;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameMode;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

@ExcludeTypeFromJacocoGeneratedReport
public class HighscoreEntryViewModel {

	public SimpleStringProperty userName = new SimpleStringProperty();

	public SimpleFloatProperty highscoreInSeconds = new SimpleFloatProperty();

	public Property<GameMode> gameMode = new SimpleObjectProperty<>();



	public HighscoreEntryViewModel(String userName, float highscoreInSeconds, GameMode gameMode) {
		this.gameMode.setValue(gameMode);
		this.userName.setValue(userName);
		this.highscoreInSeconds.setValue(highscoreInSeconds);
	}
}
