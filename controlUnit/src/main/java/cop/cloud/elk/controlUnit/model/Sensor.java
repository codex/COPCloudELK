package cop.cloud.elk.controlUnit.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Sensor {
	public enum SensorType {
		TEMPERATURE, VOLTAGE;
	}

	private String id;
	private SensorType type;
	private double min;
	private double max;
	private LocalDateTime lastSeen;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SensorType getType() {
		return type;
	}

	public void setType(SensorType type) {
		this.type = type;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public LocalDateTime getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(LocalDateTime lastSeen) {
		this.lastSeen = lastSeen;
	}

	public static Sensor create(SensorType type, double min, double max) {
		Sensor sensor = new Sensor();
		sensor.setId(UUID.randomUUID().toString());
		sensor.setType(type);
		sensor.setMax(max);
		sensor.setMin(min);
		sensor.setLastSeen(LocalDateTime.now());
		return sensor;
	}

}
