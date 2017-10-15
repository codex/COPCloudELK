package cop.cloud.elk.controlUnit.controller;

import static java.util.Arrays.stream;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cop.cloud.elk.controlUnit.model.AggregatedSensorData;
import cop.cloud.elk.controlUnit.model.AggregatedSensorData.AggregationType;
import cop.cloud.elk.controlUnit.model.Sensor;
import cop.cloud.elk.controlUnit.model.SensorData;
import net.logstash.logback.argument.StructuredArguments;

@RestController
public class Controller {
	Logger logger = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private RestTemplate restTemplate;

	private String sensorUrl = "http://sensor:8080" + "/sensors";
	private String aggregatorUrl = "http://aggregator:8081" + "/aggregate?sensorId=";

	@RequestMapping("/data")
	public List<SensorData> getSensorData() {
		List<SensorData> sensorData = new ArrayList<>();
		Sensor[] sensors = restTemplate.getForEntity(sensorUrl, Sensor[].class).getBody();
		stream(sensors).map(this::getSensorData).forEach(sensorData::add);

		return sensorData;
	}

	private SensorData getSensorData(Sensor sensor) {
		AggregatedSensorData[] aggregatedData = restTemplate
				.getForEntity(aggregatorUrl + sensor.getId(), AggregatedSensorData[].class).getBody();

		SensorData sensorData = new SensorData(sensor.getId());
		sensorData.setSensorType(sensor.getType().name());
		stream(aggregatedData).forEach((aggregatedDatum) -> sensorData.addDatum(aggregatedDatum.getValue(),
				isValueValid(sensor, aggregatedDatum), aggregatedDatum.getAggregationType().name()));

		return sensorData;
	}

	private boolean isValueValid(Sensor sensor, AggregatedSensorData aggregatedSensorData) {
		if (!AggregationType.hasValue(aggregatedSensorData.getAggregationType())) {
			return false;
		}
		double value = aggregatedSensorData.getValue();
		if (sensor.getMin() < value && sensor.getMax() > value) {
			return true;
		}
		logger.warn("invalid Values detected for {} {}", StructuredArguments.keyValue("sensorId", sensor.getId()),
				StructuredArguments.keyValue("AggregationType", aggregatedSensorData.getAggregationType().name()));
		return false;
	}

}
