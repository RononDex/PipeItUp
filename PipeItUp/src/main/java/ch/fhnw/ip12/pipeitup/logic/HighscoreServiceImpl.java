package ch.fhnw.ip12.pipeitup.logic;

import ch.fhnw.ip12.pipeitup.data.Database;
import ch.fhnw.ip12.pipeitup.data.Models.HighscoreEntry;
import ch.fhnw.ip12.pipeitup.logic.Models.HighscoreEntryModel;
import com.google.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

class HighscoreServiceImpl implements HighscoreService {

	private Database database;

	@Inject
	public HighscoreServiceImpl(Database database) {
		this.database = database;
	}

	@Override
	public List<HighscoreEntryModel> getHighscoreEntries() {
		List<HighscoreEntry> highscoreEntriesDb = database.getScores();
		return Map(highscoreEntriesDb);
	}

	private List<HighscoreEntryModel> Map(List<HighscoreEntry> highscoreEntriesDb) {
		return highscoreEntriesDb.stream()
				.map(hse -> new HighscoreEntryModel(hse.getUserName(), hse.getScoreInSeconds(), hse.getGameMode()))
				.collect(Collectors.toList());
	}

	@Override
	public void saveHighscoreEntry(HighscoreEntryModel highscoreEntryModel) {
		if (!database.getScores().equals(highscoreEntryModel.getUserName() + highscoreEntryModel.getScoreInSeconds() + highscoreEntryModel.getGameMode())) {
			database.saveScore(new HighscoreEntry(highscoreEntryModel.getUserName(),
					highscoreEntryModel.getScoreInSeconds(), highscoreEntryModel.getGameMode()));
		}
	}

	@Override
	public void clearHighscoreEntries() {
		database.clearHighscoreTable();
	}

}
