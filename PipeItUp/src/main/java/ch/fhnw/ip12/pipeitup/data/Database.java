package ch.fhnw.ip12.pipeitup.data;

import ch.fhnw.ip12.pipeitup.data.Models.GraphLayout;
import ch.fhnw.ip12.pipeitup.data.Models.HighscoreEntry;

import java.util.List;

public interface Database {
	GraphLayout getGraphLayout();

	boolean saveScore(HighscoreEntry highscoreEntry);

	List<HighscoreEntry> getScores();

	void clearHighscoreTable();
}
