package ch.fhnw.ip12.pipeitup.ui.views.touch;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
* TouchViewModel
*/
@ExcludeTypeFromJacocoGeneratedReport
public class TouchViewModel {

	public SimpleIntegerProperty currentSum = new SimpleIntegerProperty(0);

	public SimpleIntegerProperty currentSecondsPassed = new SimpleIntegerProperty(0);

	public SimpleObjectProperty<TouchScene> currentScene = new SimpleObjectProperty<TouchScene>(TouchScene.START_SCREEN);
}
