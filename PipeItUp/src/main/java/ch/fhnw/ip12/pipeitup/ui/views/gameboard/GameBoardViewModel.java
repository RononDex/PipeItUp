package ch.fhnw.ip12.pipeitup.ui.views.gameboard;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalTime;

/**
 * GameBoardViewModel
 */
@ExcludeTypeFromJacocoGeneratedReport
public class GameBoardViewModel {

	public Property<GraphViewModel> graphViewModel = new SimpleObjectProperty<>();

	public Property<GameMode> gameMode = new SimpleObjectProperty<>();
	public Property<GameBoardState> gameBoardState = new SimpleObjectProperty<>();

	public Property<EdgeViewModel> selectedEdgeForValidation = new SimpleObjectProperty<>();
	public Property<VertexViewModel> startNodeForPrim = new SimpleObjectProperty<>();

	public Property<LocalTime> gameStartMomentForHighscore = new SimpleObjectProperty<>();
	public Property<LocalTime> gameEndMomentForHighscore = new SimpleObjectProperty<>();

	public Property<Boolean> resetGameBoard = new SimpleObjectProperty<>();

	public Property<MapMode> selectedMapMode = new SimpleObjectProperty<>();

	public Property<Boolean> startNewGame = new SimpleBooleanProperty();
	public Property<Boolean> showSolution = new SimpleBooleanProperty();
}
