package ch.fhnw.ip12.pipeitup.ui;

import org.junit.Test;

/**
* PipeItUpGameTests
*/
public class PipeItUpGameTests {

	@Test
	public void Test() {
		PipeItUpGame game = new PipeItUpGame(UiMode.HARDWARE);
		game.start();
	}
	
}
