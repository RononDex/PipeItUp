package ch.fhnw.ip12.pipeitup.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import ch.fhnw.ip12.pipeitup.logic.GraphLayoutLoader;
import ch.fhnw.ip12.pipeitup.ui.views.touch.TouchScene;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * ViewModelServiceImplTests
 */
public class ViewModelServiceImplTests {

	@Test
	public void createStartUpViewModel_WithNoParameters_ReturnsNewViewModelInStartUpState() {
		// Arrange
		GraphLayoutLoader graphLayoutLoaderMock = mock(GraphLayoutLoader.class);
		ViewModelServiceImpl testee = new ViewModelServiceImpl(graphLayoutLoaderMock);

		// Act
		PipeItUpGameViewModel actual = testee.createStartUpViewModel();

		// Assert
		assertNotNull(actual);
		assertNotNull(actual.touchViewModel);
		assertNotNull(actual.gameBoardViewModel);
		assertEquals(0, actual.touchViewModel.currentSum.get());
		assertEquals(0, actual.touchViewModel.currentSecondsPassed.get());
		assertEquals(TouchScene.START_SCREEN, actual.touchViewModel.currentScene.get());
	}

}
