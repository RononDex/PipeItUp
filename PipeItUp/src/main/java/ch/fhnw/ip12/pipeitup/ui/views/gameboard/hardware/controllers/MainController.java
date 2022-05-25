package ch.fhnw.ip12.pipeitup.ui.views.gameboard.hardware.controllers;

import ch.fhnw.ip12.pipeitup.app.ExcludeMethodFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import ch.fhnw.ip12.pipeitup.app.PipeItUp;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeState;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.EdgeViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoardState;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameBoardViewModel;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.GameMode;
import ch.fhnw.ip12.pipeitup.ui.views.gameboard.VertexViewModel;
import com.diozero.api.GpioPullUpDown;
import com.diozero.devices.Button;
import com.diozero.devices.MCP23017;
import com.github.mbelling.ws281x.Color;
import com.github.mbelling.ws281x.LedStrip;
import com.github.mbelling.ws281x.LedStripType;
import com.github.mbelling.ws281x.Ws281xLedStrip;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

@ExcludeTypeFromJacocoGeneratedReport
public class MainController {
	private GameBoardViewModel gameBoardViewModel;
	private LedStrip led0;
	private LedStrip led1;
	private final MCP23017[] mcp = new MCP23017[4];
	private boolean edgeBlinkingColorToggle;
	public Thread buttonListener;
	private final HashMap<HashMap<String, Integer>, Button[]> edgeHWButtonMap = new HashMap<>();
	private static final Logger log = LogManager.getLogger(PipeItUp.class.getName());

	public MainController(GameBoardViewModel gameBoardViewModel) {
		this.gameBoardViewModel = gameBoardViewModel;
	}

	public void initialize() {
		initializeMCPs();
		initializeButtons();
		resetLEDs();
		initializeBlinkingTimer(Color.WHITE, Color.YELLOW);
		buttonListener = buttonListenerThread();
		buttonListener.start();

		//gameBoardViewModel.startNodeForPrim.addListener(listener -> updateGameBoardLEDs());
		gameBoardViewModel.selectedEdgeForValidation.addListener(listener -> updateGameBoardLEDs());
		gameBoardViewModel.graphViewModel.addListener(listener -> {
			initializeButtons();
			resetLEDs();
			updateGameBoardLEDs();
		});
		gameBoardViewModel.gameBoardState.addListener(listener -> updateGameBoardLEDs());
		gameBoardViewModel.graphViewModel.addListener(listener -> bindEdgesAndVertices());
		//gameBoardViewModel.gameMode.addListener(listener -> updateGameModeLabel());
		updateGameBoardLEDs();
	}

	private void resetLEDs() {
		this.led0 = new Ws281xLedStrip(
				81,
				18,
				800000,
				10,
				5,
				0,
				false,
				LedStripType.WS2811_STRIP_RGB,
				false);
		this.led1 = new Ws281xLedStrip(
				91,
				19,
				800000,
				10,
				5,
				1,
				false,
				LedStripType.WS2811_STRIP_RGB,
				false);
	}

	private boolean onEdgeClick(EdgeViewModel edge) {
		if (gameBoardViewModel.gameBoardState.getValue() == GameBoardState.SELECT_NEXT_EDGE) {
			log.info("Edge clicked connecting vertices " + edge.vertex1.getVertexNumber() + " and "
					+ edge.vertex2.getVertexNumber());
			if (edge.edgeState.get() != EdgeState.INVALID_SELECTION && edge.edgeState.get() != EdgeState.SELECTED)
				gameBoardViewModel.selectedEdgeForValidation.setValue(edge);

			return true;
		}

		return false;
	}


	private void updateGameBoardLEDs() {
		clearLEDLines();
		log.info("GAME STATE:" + gameBoardViewModel.gameBoardState.getValue().name());
		if (gameBoardViewModel == null || gameBoardViewModel.graphViewModel == null) {
			return;
		}
		setEdgeColors();

		if (gameBoardViewModel.gameBoardState.getValue() == GameBoardState.GAME_FINISHED)
			victoryAnimation();

		if (gameBoardViewModel.gameMode.getValue() == GameMode.PRIM
				&& gameBoardViewModel.startNodeForPrim.getValue() != null
				&& gameBoardViewModel.gameBoardState.getValue() == GameBoardState.SELECT_NEXT_EDGE)
			setVertexColor(gameBoardViewModel.startNodeForPrim.getValue(), Color.GREEN);

		renderLEDLines();
	}

	private void setEdgeColors() {
		for (EdgeViewModel edge : gameBoardViewModel.graphViewModel.getValue().edgeViewModels) {
			switch (edge.edgeState.get()) {
				case SELECTED:
					setEdgeColor(edge, Color.RED);
					break;
				case INVALID_SELECTION:
					setEdgeColor(edge, Color.GREEN);
					break;
				case UNSELECTED:
					setEdgeColor(edge, Color.BLUE);
			}
		}
		for (VertexViewModel vertex : gameBoardViewModel.graphViewModel.getValue().vertexViewModels) {
			if (vertex.isLedActive.getValue()) setVertexColor(vertex, Color.YELLOW);
		}
	}

	private void initializeMCPs() {
		mcp[0] = new MCP23017(1, 32, -1, -1);
		mcp[1] = new MCP23017(1, 33, -1, -1);
		mcp[2] = new MCP23017(1, 34, -1, -1);
		mcp[3] = new MCP23017(1, 35, -1, -1);
	}

	public void clearLEDLines() {
		led0.setStrip(0, 0, 0);
		led1.setStrip(0, 0, 0);
	}

	public void renderLEDLines() {
		led0.render();
		led1.render();
	}

	private void initializeButtons() {
		if (edgeHWButtonMap.isEmpty())
			gameBoardViewModel.graphViewModel.getValue().edgeViewModels.stream().map(EdgeViewModel::getHardwareInfo).forEach(
					hwInfo -> edgeHWButtonMap.put(hwInfo, new Button[]{
							new Button(mcp[hwInfo.get("mcp1")], hwInfo.get("pin1"), GpioPullUpDown.PULL_UP),
							new Button(mcp[hwInfo.get("mcp2")], hwInfo.get("pin2"), GpioPullUpDown.PULL_UP)
					})
			);
		for (EdgeViewModel edge : gameBoardViewModel.graphViewModel.getValue().edgeViewModels) {
			if (edge.getButton1() == null || edge.getButton2() == null) {
				edge.setButton1(edgeHWButtonMap.get(edge.getHardwareInfo())[0]);
				edge.setButton2(edgeHWButtonMap.get(edge.getHardwareInfo())[1]);
			}
		}
	}

	private void setEdgeColor(EdgeViewModel edge, Color color) {
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

	private void setVertexColor(VertexViewModel vertex, Color color) {
		LedStrip activeLine;
		if (vertex.getLEDLine() == 0) {
			activeLine = led0;
		} else {
			activeLine = led1;
		}
		activeLine.setPixel(vertex.getLED(), color);
	}

	private void initializeBlinkingTimer(Color color1, Color color2) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			@ExcludeMethodFromJacocoGeneratedReport
			public void run() {
				gameBoardViewModel.graphViewModel.getValue().edgeViewModels.stream().filter(edgeViewModel -> edgeViewModel.edgeState.getValue() == EdgeState.BLINKING).forEach(
						edge -> {
							if (edgeBlinkingColorToggle)
								setEdgeColor(edge, color2);
							else setEdgeColor(edge, color1);
						}
				);
				edgeBlinkingColorToggle = !edgeBlinkingColorToggle;
				renderLEDLines();
			}
		}, 0, 1000);
	}

	private void victoryAnimation() {
		for (EdgeViewModel edge : gameBoardViewModel.graphViewModel.getValue().edgeViewModels) {
			setEdgeColor(edge, Color.MAGENTA);
		}
		for (VertexViewModel vertex : gameBoardViewModel.graphViewModel.getValue().vertexViewModels) {
			setVertexColor(vertex, Color.PINK);
		}
	}

	private Thread buttonListenerThread() {
		return new Thread(() -> {
			try {
				while (true) {
					for (EdgeViewModel edge : gameBoardViewModel.graphViewModel.getValue().edgeViewModels) {
						if (edge.getButton1().isPressed() || edge.getButton2().isPressed()) {
							if (onEdgeClick(edge)) {
								Thread.sleep(100);
								break;
							}
						}
					}
					Thread.sleep(50);
				}
			} catch (NullPointerException e) {
				log.fatal(e.getMessage());
			} catch (InterruptedException e) {
				log.info("ButtonListener interrupted: " + e.getMessage());
			}
		});
	}

	private void bindEdgesAndVertices() {
		gameBoardViewModel.graphViewModel.getValue().edgeViewModels
				.forEach(e -> e.weight.addListener(listener -> updateGameBoardLEDs()));
		gameBoardViewModel.graphViewModel.getValue().edgeViewModels
				.forEach(e -> e.edgeState.addListener(listener -> updateGameBoardLEDs()));
		gameBoardViewModel.graphViewModel.getValue().vertexViewModels
				.forEach(v -> v.isLedActive.addListener(listener -> updateGameBoardLEDs()));
	}
}
