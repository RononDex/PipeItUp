package ch.fhnw.ip12.pipeitup.logic.Models;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;

@ExcludeTypeFromJacocoGeneratedReport
public class HighscoreEntryModel {

	private String userName;
	private float scoreInSeconds;

	public HighscoreEntryModel(String userName, float scoreInSeconds) {
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
