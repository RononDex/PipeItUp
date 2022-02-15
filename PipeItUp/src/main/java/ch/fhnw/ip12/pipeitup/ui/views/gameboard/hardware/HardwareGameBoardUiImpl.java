
package ch.fhnw.ip12.pipeitup.ui.views.gameboard.hardware;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.app.PipeItUp;
import com.diozero.api.GpioPullUpDown;
import com.diozero.devices.Button;
import com.diozero.util.SleepUtil;
import com.github.mbelling.ws281x.Color;
import com.github.mbelling.ws281x.LedStrip;
import com.github.mbelling.ws281x.LedStripType;
import com.github.mbelling.ws281x.Ws281xLedStrip;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoard;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoardViewModel;

/**
 * HardwareGameBoard
 */
@ExcludeTypeFromJacocoGeneratedReport
public class HardwareGameBoardUiImpl implements HardwareGameBoardUi {

	static final Logger log = LogManager.getLogger(PipeItUp.class.getName());
	private GameBoardViewModel gameBoardViewModel;

	@Override
	public void start() {
		LedStrip led = new Ws281xLedStrip(
				8,
				18,
				800000,
				10,
				60,
				0,
				false,
				LedStripType.WS2811_STRIP_RGB,
				true);
		led.setStrip(Color.CYAN);
		led.render();
		SleepUtil.sleepSeconds(2);
		led.setStrip(0,0,0);
		led.setPixel(0,Color.BLUE);
		led.setPixel(5,Color.GREEN);
		led.setPixel(7,Color.MAGENTA);
		led.render();
		log.info("LED Count: " + led.getLedsCount());
		try (Button button = new Button(22, GpioPullUpDown.PULL_DOWN)) {
			long start = System.currentTimeMillis();
			long end = start + TimeUnit.MILLISECONDS.convert(10, TimeUnit.SECONDS);
			while (System.currentTimeMillis() < end) {
				if (button.isPressed()) {
					log.info("button pressed");
					led.setStrip(Color.YELLOW);
					led.render();
					SleepUtil.sleepSeconds(1);
				}
			}

		}
	}

	@Override
	public void setGameBoardViewModel(GameBoardViewModel gameBoardViewModel) {
		this.gameBoardViewModel = gameBoardViewModel;
		
	}
}
