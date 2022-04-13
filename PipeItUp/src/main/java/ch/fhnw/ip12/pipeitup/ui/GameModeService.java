package ch.fhnw.ip12.pipeitup.ui;

public interface GameModeService {

	PipeItUpGameViewModel createStartUpViewModel();

	void validateSelectedEdge(PipeItUpGameViewModel viewModel);

	void loadRandomWeightedGraph(PipeItUpGameViewModel viewModel);

}
