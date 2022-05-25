package ch.fhnw.ip12.pipeitup.logic;

import ch.fhnw.ip12.pipeitup.logic.Models.HighscoreEntryModel;

import java.util.List;

public interface HighscoreService {

	List<HighscoreEntryModel> getHighscoreEntries();

	void saveHighscoreEntry(HighscoreEntryModel highscoreEntryModel);

	void clearHighscoreEntries();
}
