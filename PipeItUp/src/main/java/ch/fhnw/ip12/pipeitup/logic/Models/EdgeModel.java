package ch.fhnw.ip12.pipeitup.logic.Models;

import ch.fhnw.ip12.pipeitup.app.ExcludeTypeFromJacocoGeneratedReport;
import com.diozero.devices.Button;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * EdgeModel
 */
@ExcludeTypeFromJacocoGeneratedReport
public class EdgeModel {

	private VertexModel vertex1;
	private VertexModel vertex2;
	private HashMap<String, Integer> hardwareInfo;
	private int weight;
	private boolean isUsed = false;

	public EdgeModel(VertexModel vertex1, VertexModel vertex2, int weight) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = weight;
	}

	public EdgeModel(VertexModel vertex1, VertexModel vertex2, int weight,  HashMap<String, Integer> hardwareInfo) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = weight;
		this.hardwareInfo = hardwareInfo;

	}

	public VertexModel getVertex1() {
		return vertex1;
	}

	public VertexModel getVertex2() {
		return vertex2;
	}

	public List<VertexModel> getConnectedVertices() {
		return Arrays.asList(vertex1, vertex2);
	}

	public int getWeight() {
		return weight;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public HashMap<String, Integer> getHardwareInfo() {
		return hardwareInfo;
	}
}
