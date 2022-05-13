package ch.fhnw.ip12.pipeitup.data.Models;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import com.diozero.devices.Button;

import java.util.HashMap;

@ExcludeTypeFromJacocoGeneratedReport
public class Edge {

	private Vertex vertex1;
	private Vertex vertex2;
	private HashMap<String, Integer> hardwareInfo;


	public Edge(Vertex vertex1, Vertex vertex2) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
	}

	public Edge(Vertex vertex1, Vertex vertex2, int firstLED, int LEDLine, int mcp1, int button1, int mcp2, int button2) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		hardwareInfo = new HashMap<>();
		hardwareInfo.put("firstLED", firstLED);
		hardwareInfo.put("LEDLine", LEDLine);
		hardwareInfo.put("mcp1", mcp1);
		hardwareInfo.put("button1", button1);
		hardwareInfo.put("mcp2", mcp2);
		hardwareInfo.put("button2", button2);

	}

	public Vertex getVertex1() {
		return vertex1;
	}

	public Vertex getVertex2() {
		return vertex2;
	}

	public HashMap<String, Integer> getHardwareInfo() {
		return hardwareInfo;
	}
}
