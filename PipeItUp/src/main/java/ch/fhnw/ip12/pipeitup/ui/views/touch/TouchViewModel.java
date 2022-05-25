package ch.fhnw.ip12.pipeitup.ui.views.touch;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

/**
* TouchViewModel
*/
@ExcludeTypeFromJacocoGeneratedReport
public class TouchViewModel {

	public SimpleIntegerProperty currentSum = new SimpleIntegerProperty(0);

	public SimpleIntegerProperty currentSecondsPassed = new SimpleIntegerProperty(0);

	public SimpleObjectProperty<TouchScene> currentScene = new SimpleObjectProperty<TouchScene>(TouchScene.START_SCREEN);

	public SimpleStringProperty userName = new SimpleStringProperty("");
	public SimpleListProperty<HighscoreEntryViewModel> highscoreEntries = new SimpleListProperty<>(FXCollections.emptyObservableList());

	public SimpleObjectProperty<HighscoreEntryViewModel> newHighscoreEntryToSave = new SimpleObjectProperty<>();
	public Property<Boolean> deleteAllHighscoreEntries = new SimpleBooleanProperty();

	public BooleanProperty shutdownPipeItUp = new SimpleBooleanProperty();
}
