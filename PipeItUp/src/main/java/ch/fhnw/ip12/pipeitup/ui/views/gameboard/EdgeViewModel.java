package ch.fhnw.ip12.pipeitup.ui.views.gameboard;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import com.diozero.devices.Button;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.HashMap;

@ExcludeTypeFromJacocoGeneratedReport
public class EdgeViewModel {
	public VertexViewModel vertex1;

	public VertexViewModel vertex2;

	public SimpleIntegerProperty weight = new SimpleIntegerProperty(1);
	private HashMap<String, Integer> hardwareInfo;
	private Button button1;
	private Button button2;

	public SimpleObjectProperty<EdgeState> edgeState = new SimpleObjectProperty<EdgeState>(EdgeState.UNSELECTED);

	public EdgeViewModel(VertexViewModel vertex1, VertexViewModel vertex2, int weight,  HashMap<String, Integer> hardwareInfo) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight.set(weight);
		this.hardwareInfo = hardwareInfo;

	}

	public int getFirstLED() {
		return hardwareInfo.get("firstLED");
	}

	public int getLEDLine() {
		return hardwareInfo.get("LEDLine");
	}

	public void setButton1(Button button1) {
		this.button1 = button1;
	}

	public void setButton2(Button button2) {
		this.button2 = button2;
	}

	public Button getButton1() {
		return button1;
	}

	public Button getButton2() {
		return button2;
	}

	public HashMap<String, Integer> getHardwareInfo() {
		return hardwareInfo;
	}
}

