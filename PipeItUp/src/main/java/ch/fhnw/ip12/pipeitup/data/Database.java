package ch.fhnw.ip12.pipeitup.data;

import java.util.List;

import ch.fhnw.ip12.pipeitup.data.Models.GraphLayout;
import ch.fhnw.ip12.pipeitup.data.Models.HighscoreEntry;

public interface Database {
	GraphLayout getGraphLayout();

	boolean saveScore(HighscoreEntry highscoreEntry);

	List<HighscoreEntry> getScores();
}
