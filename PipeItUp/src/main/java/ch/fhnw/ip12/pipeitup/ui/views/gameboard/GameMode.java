package ch.fhnw.ip12.pipeitup.ui.views.gameboard;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;

import java.util.Arrays;
import java.util.Optional;

@ExcludeTypeFromJacocoGeneratedReport
public enum GameMode {

	PRIM(0),
	KRUSKAL(1);

	public final int value;

	GameMode(int value) {
		this.value = value;
	}

	public static Optional<GameMode> valueOf(int value) {
        return Arrays.stream(values())
            .filter(gameMode -> gameMode.value == value)
            .findFirst();
    }

	public int getValue() {
		return value;
	}
}
