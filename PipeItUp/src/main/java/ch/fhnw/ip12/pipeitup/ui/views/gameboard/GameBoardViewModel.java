package ch.fhnw.ip12.pipeitup.ui.views.gameboard;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

/**
 * GameBoardViewModel
 */
@ExcludeTypeFromJacocoGeneratedReport
public class GameBoardViewModel {

	Property<GraphViewModel> graphViewModel = new SimpleObjectProperty<GraphViewModel>(new GraphViewModel());

	public Property<GraphViewModel> getGraphViewModel() {
		return graphViewModel;
	}
}
