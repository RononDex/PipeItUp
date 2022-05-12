package ch.fhnw.ip12.pipeitup.logic;

import java.util.List;

import ch.fhnw.ip12.pipeitup.logic.Models.HighscoreEntryModel;

public interface HighscoreService {

	List<HighscoreEntryModel> getHighscoreEntries();

	void saveHighscoreEntry(HighscoreEntryModel highscoreEntryModel);
}
