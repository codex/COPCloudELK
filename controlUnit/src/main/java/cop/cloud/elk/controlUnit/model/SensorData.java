package cop.cloud.elk.controlUnit.model;

import java.util.ArrayList;
import java.util.List;

public class SensorData {

	private String sensorId;

	private String sensorType;

	private List<SensorDatum> data;

	public SensorData() {
		//
	}

	public SensorData(String sensorId) {
		this.sensorId = sensorId;
		this.data = new ArrayList<SensorDatum>();
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public List<SensorDatum> getData() {
		return data;
	}

	public void setData(List<SensorDatum> data) {
		this.data = data;
	}

	public void addDatum(double value, boolean isValid, String type) {
		data.add(new SensorDatum(value, isValid, type));
	}

	public String getSensorType() {
		return sensorType;
	}

	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
	}
}
