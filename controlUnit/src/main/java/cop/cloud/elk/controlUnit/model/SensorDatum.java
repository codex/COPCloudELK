package cop.cloud.elk.controlUnit.model;

public class SensorDatum {

	private boolean isValid;
	private double value;
	private String type;

	public SensorDatum() {
		//
	}

	public SensorDatum(double value, boolean isValid, String type) {
		this.value = value;
		this.isValid = isValid;
		this.type = type;
	}

	public boolean isValid() {
		return isValid;
	}

	public double getValue() {
		return value;
	}

	public String getType() {
		return type;
	}

}
