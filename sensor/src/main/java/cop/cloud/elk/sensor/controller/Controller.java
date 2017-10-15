package cop.cloud.elk.sensor.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cop.cloud.elk.sensor.model.Sensor;
import cop.cloud.elk.sensor.model.Sensor.SensorType;
import cop.cloud.elk.sensor.model.SensorData;

@RestController
public class Controller {
	private static final int OFFSET = 1;

	Logger logger = LoggerFactory.getLogger(Controller.class);

	private List<Sensor> sensors = initalizeSensors();

	private List<Sensor> initalizeSensors() {
		Sensor temperatureSensor = Sensor.create(SensorType.TEMPERATURE, -4, 60.5);
		Sensor voltageSensor = Sensor.create(SensorType.VOLTAGE, 4, 40.5);

		return Arrays.asList(temperatureSensor, voltageSensor);
	}

	@RequestMapping("/sensors")
	public List<Sensor> getSensors() {
		return sensors;
	}

	@RequestMapping("/sensor/{sensorId}")
	public SensorData getSensorData(@PathVariable("sensorId") String sensorId) {
		Sensor sensor = findSensor(sensorId);
		return createFakeValues(sensor);
	}

	private Sensor findSensor(String sensorId) {
		Optional<Sensor> findFirst = sensors.stream().filter((sensor) -> sensor.getId().equals(sensorId)).findFirst();
		return findFirst.get();
	}

	private SensorData createFakeValues(Sensor sensor) {
		SensorData sensorData = new SensorData(sensor.getId());
		LocalDateTime currentTime = LocalDateTime.now();
		Duration durationSinceLastGetData = Duration.between(sensor.getLastSeen(), currentTime);

		Random random = new Random();
		random.doubles(durationSinceLastGetData.getSeconds() * 10, sensor.getMin() - OFFSET, sensor.getMax() + OFFSET)
				.forEach(sensorData::addValue);
		sensor.setLastSeen(currentTime);
		return sensorData;
	}
}
