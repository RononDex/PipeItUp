package ch.fhnw.ip12.pipeitup.ui;

import ch.fhnw.ip12.pipeitup.app.ExcludeMethodFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;

/**
* Color
*/
@ExcludeTypeFromJacocoGeneratedReport
public class Color {

	int r;
	int g;
	int b;

	public Color(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	@ExcludeMethodFromJacocoGeneratedReport
	public int getR() {
		return r;
	}

	@ExcludeMethodFromJacocoGeneratedReport
	public int getG() {
		return g;
	}

	@ExcludeMethodFromJacocoGeneratedReport
	public int getB() {
		return b;
	}
}
