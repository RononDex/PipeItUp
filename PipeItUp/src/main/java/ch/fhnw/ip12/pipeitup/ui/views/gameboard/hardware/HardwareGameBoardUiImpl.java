
package ch.fhnw.ip12.pipeitup.ui.views.gameboard.hardware;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.app.PipeItUp;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeState;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoardViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.VertexViewModel;
import com.diozero.devices.MCP23017;
import com.github.mbelling.ws281x.Color;
import com.github.mbelling.ws281x.LedStrip;
import com.github.mbelling.ws281x.LedStripType;
import com.github.mbelling.ws281x.Ws281xLedStrip;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * HardwareGameBoard
 */
@ExcludeTypeFromJacocoGeneratedReport
public class HardwareGameBoardUiImpl implements HardwareGameBoardUi {

	static final Logger log = LogManager.getLogger(PipeItUp.class.getName());
	private GameBoardViewModel gameBoardViewModel;
	private LedStrip led0;
	private LedStrip led1;


	@Override
	public void start() {
		led0 = new Ws281xLedStrip(
			81,
			18,
			800000,
			10,
			5,
			0,
			false,
			LedStripType.WS2811_STRIP_RGB,
			false);
		led1 = new Ws281xLedStrip(
			91,
			19,
			800000,
			10,
			5,
			1,
			false,
			LedStripType.WS2811_STRIP_RGB,
			false);
		clearLEDLines();
		//List<MCP23017> mcp = new ArrayList<>();
		//mcp.add(new MCP23017(1, 32, -1, -1));
		//mcp.add(new MCP23017(1, 33, -1, -1));
		//mcp.add(new MCP23017(1, 34, -1, -1));
		//mcp.add(new MCP23017(1, 35, -1, -1));
		//int i = 0;
		//Button b1 = new Button(mcp.get(0), 0, GpioPullUpDown.PULL_UP);
		//Button b2 = new Button(mcp.get(0), 1, GpioPullUpDown.PULL_UP);
		//Button b3 = new Button(mcp.get(1), 0, GpioPullUpDown.PULL_UP);
		//Button b4 = new Button(mcp.get(1), 1, GpioPullUpDown.PULL_UP);
		//Button b5 = new Button(mcp.get(2), 0, GpioPullUpDown.PULL_UP);
		//Button b6 = new Button(mcp.get(3), 0, GpioPullUpDown.PULL_UP);
//
		//for (EdgeViewModel edge : gameBoardViewModel.graphViewModel.getValue().edgeViewModels) {
		//	System.out.println(i);
		//	i++;
		//	System.out.println("MCP NUMMER: " + edge.getHardwareInfo().get("mcp1"));
		//	System.out.println(edge.getHardwareInfo().get("pin1"));
		//	edge.setButton1(new Button(mcp.get(edge.getHardwareInfo().get("mcp1")), edge.getHardwareInfo().get("pin1"),
		//		GpioPullUpDown.PULL_UP));
		//	edge.setButton2(new Button(mcp.get(edge.getHardwareInfo().get("mcp2")), edge.getHardwareInfo().get("pin2"),
		//		GpioPullUpDown.PULL_UP));
		//}
	}

	@Override
	public void setEdgeColor(EdgeViewModel edge, Color color) {
		LedStrip activeLine;
		if (edge.getLEDLine() == 0) {
			activeLine = led0;
		} else {
			activeLine = led1;
		}
		for (int i = edge.getFirstLED(); i < edge.getFirstLED() + edge.weight.get(); i++) {
			activeLine.setPixel(i, color);
		}
	}

	@Override
	public void setVertexColor(VertexViewModel vertex, Color color) {
		LedStrip activeLine;
		if (vertex.getLEDLine() == 0) {
			activeLine = led0;
		} else {
			activeLine = led1;
		}
		activeLine.setPixel(vertex.getLED(), color);
	}

	@Override
	public void victoryRoyal(Set<EdgeViewModel> edgeViewModels) {
		for (EdgeViewModel edge : edgeViewModels) {
			if (edge.edgeState.get() == EdgeState.SELECTED) {
				setEdgeColor(edge, Color.MAGENTA);
			}
		}
		renderLEDLines();
	}

	@Override
	public void renderLEDLines() {
		led0.render();
		led1.render();
	}

	@Override
	public void clearLEDLines() {
		led0.setStrip(0, 0, 0);
		led1.setStrip(0, 0, 0);
		renderLEDLines();
	}

	@Override
	public void setGameBoardViewModel(GameBoardViewModel gameBoardViewModel) {
		this.gameBoardViewModel = gameBoardViewModel;
	}
}
