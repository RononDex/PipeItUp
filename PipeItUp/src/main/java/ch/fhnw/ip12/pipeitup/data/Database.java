package ch.fhnw.ip12.pipeitup.data;

import ch.fhnw.ip12.pipeitup.data.Models.GraphLayout;

import java.util.ArrayList;

public interface Database {
	GraphLayout getGraphLayout();

	boolean saveScore(String name, int score);

	//TODO: define return type
	void getScores();

}
