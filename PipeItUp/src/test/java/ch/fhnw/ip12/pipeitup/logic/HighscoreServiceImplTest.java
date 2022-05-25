package ch.fhnw.ip12.pipeitup.logic;

import ch.fhnw.ip12.pipeitup.data.Database;
import ch.fhnw.ip12.pipeitup.data.Models.HighscoreEntry;
import ch.fhnw.ip12.pipeitup.logic.Models.HighscoreEntryModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HighscoreServiceImplTest {

	@Test
	void getHighscoreEntries_With1HighsoreEntryInDatabase_ReturnsTheSameEntry() {
		Database databaseMock = mock(Database.class);
		List<HighscoreEntry> databaseEntries = new ArrayList<>();
		databaseEntries.add(new HighscoreEntry("testUser", 1000.0f, 0));
		when(databaseMock.getScores()).thenReturn(databaseEntries);
		HighscoreServiceImpl testee = new HighscoreServiceImpl(databaseMock);

		List<HighscoreEntryModel> actual = testee.getHighscoreEntries();

		assertEquals(1, actual.size());
		assertEquals("testUser", actual.get(0).getUserName());
		assertEquals(1000.0, actual.get(0).getScoreInSeconds());
	}

	@Test
	void saveHighscoreEntry_WithNewEntry_CallsDatabase() {
		Database databaseMock = mock(Database.class);
		HighscoreServiceImpl testee = new HighscoreServiceImpl(databaseMock);

		testee.saveHighscoreEntry(new HighscoreEntryModel("testUser", 487.87f, 0));

		verify(databaseMock, times(1)).saveScore(any());
	}

}
