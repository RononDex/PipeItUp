package ch.fhnw.ip12.pipeitup.data.Models;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;

@ExcludeTypeFromJacocoGeneratedReport
public class HighscoreEntry {

	private String userName;
	private float scoreInSeconds;
	private int gameMode;

	public HighscoreEntry(String userName, float scoreInSeconds, int gameMode) {
		this.userName = userName;
		this.scoreInSeconds = scoreInSeconds;
		this.gameMode = gameMode;
	}

	public String getUserName() {
		return userName;
	}

	public float getScoreInSeconds() {
		return scoreInSeconds;
	}

	public int getGameMode() {
		return gameMode;
	}
}
