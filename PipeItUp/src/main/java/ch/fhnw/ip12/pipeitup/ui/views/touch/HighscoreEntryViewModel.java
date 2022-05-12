package ch.fhnw.ip12.pipeitup.ui.views.touch;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

@ExcludeTypeFromJacocoGeneratedReport
public class HighscoreEntryViewModel {

	public SimpleStringProperty userName = new SimpleStringProperty();

	public SimpleFloatProperty highscoreInSeconds = new SimpleFloatProperty();

	public HighscoreEntryViewModel(String userName, float highscoreInSeconds) {
		this.userName.setValue(userName);
		this.highscoreInSeconds.setValue(highscoreInSeconds);
	}
}
