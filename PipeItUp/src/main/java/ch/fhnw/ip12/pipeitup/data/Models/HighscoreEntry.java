package ch.fhnw.ip12.pipeitup.data.Models;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;

@ExcludeTypeFromJacocoGeneratedReport
public class HighscoreEntry {

	private String userName;
	private float scoreInSeconds;

	public HighscoreEntry(String userName, float scoreInSeconds) {
		this.userName = userName;
		this.scoreInSeconds = scoreInSeconds;
	}

	public String getUserName() {
		return userName;
	}

	public float getScoreInSeconds() {
		return scoreInSeconds;
	}
}
