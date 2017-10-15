package cop.cloud.elk.aggregator.model;

import java.util.ArrayList;
import java.util.List;

public class SensorData {

	private String sensorId;

	private List<Double> values;

	public SensorData() {
		//
	}

	public SensorData(String sensorId) {
		this.sensorId = sensorId;
		this.values = new ArrayList<Double>();
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public List<Double> getValues() {
		return values;
	}

	public void addValue(double value) {
		values.add(value);
	}
}
