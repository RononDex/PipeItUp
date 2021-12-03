package ch.fhnw.ip12.pipeitup.ui;

import com.google.inject.Inject;

import ch.fhnw.ip12.pipeitup.logic.GraphLayoutLoader;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchViewModel;

/**
* ViewModelService
*/
class ViewModelServiceImpl implements ViewModelService {

	private GraphLayoutLoader graphLayoutLoader;

	@Inject
	public ViewModelServiceImpl(GraphLayoutLoader graphLayoutLoader) {
		this.graphLayoutLoader = graphLayoutLoader;
	}

	public PipeItUpGameViewModel CreateStartUpViewModel()
	{
		PipeItUpGameViewModel viewModel = new PipeItUpGameViewModel();
		viewModel.touchViewModel = new TouchViewModel();
		return viewModel;
	}
	
}
