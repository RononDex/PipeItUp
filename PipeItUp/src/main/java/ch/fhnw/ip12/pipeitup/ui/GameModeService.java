package ch.fhnw.ip12.pipeitup.ui;

public interface GameModeService {

	PipeItUpGameViewModel createStartUpViewModel();

	void validateSelectedEdge(PipeItUpGameViewModel viewModel);

	void loadRandomWeightedGraph(PipeItUpGameViewModel viewModel);

	void setStartNodeForPrim(PipeItUpGameViewModel viewModel);

	void resetGameBoard(PipeItUpGameViewModel viewModel);

	void startNewGame(PipeItUpGameViewModel viewModel);

	void showSolution(PipeItUpGameViewModel viewModel);

	void deleteAllHighscoreEntries(PipeItUpGameViewModel viewModel);

	void saveNewHighscoreEntry(PipeItUpGameViewModel viewModel);
}
