package cop.cloud.elk.aggregator.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cop.cloud.elk.aggregator.model.AggregatedSensorData;
import cop.cloud.elk.aggregator.model.AggregatedSensorData.AggregationType;
import cop.cloud.elk.aggregator.model.SensorData;

@RestController
public class Controller {
	Logger logger = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private RestTemplate restTemplate;

	private String sensorDataUrl = "http://sensor:8080" + "/sensor/";

	@RequestMapping("/aggregate")
	public List<AggregatedSensorData> getAggregatedSensorData(@RequestParam("sensorId") String sensorId) {
		String temperaturSensorUrl = sensorDataUrl + sensorId;
		SensorData sensorData = restTemplate.getForObject(temperaturSensorUrl, SensorData.class);
		return aggregate(sensorData);
	}

	private List<AggregatedSensorData> aggregate(SensorData sensorData) {
		AggregatedSensorData max = new AggregatedSensorData(sensorData.getSensorId(), AggregationType.MAX);
		max.setValue(toDoubleStream(sensorData).max().getAsDouble());
		AggregatedSensorData min = new AggregatedSensorData(sensorData.getSensorId(), AggregationType.MIN);
		min.setValue(toDoubleStream(sensorData).min().getAsDouble());
		AggregatedSensorData average = new AggregatedSensorData(sensorData.getSensorId(), AggregationType.MEAN);
		average.setValue(toDoubleStream(sensorData).average().getAsDouble());

		return Arrays.<AggregatedSensorData> asList(max, min, average);
	}

	private DoubleStream toDoubleStream(SensorData sensorData) {
		return sensorData.getValues().stream().mapToDouble(Double::doubleValue);
	}

}
