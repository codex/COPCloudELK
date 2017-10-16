# Project Title

## Tasks:
* Build and Run everything, then open the index.html in a Browser and Kibana in another Tab.
* Add a line graph visualisation for the Field "DurationMs" containing average and max Duration for all 3 Microservices and add it to a new Dashboard
* Add SessionID's to the MDC parameters of the existing logging and watch the effect on Kibana
* Hand over the SessionID to the next Microservice using Headers and watch the effect on Kibana
* Add further visualisations of your own choice and or extend the services

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

Make sure the following are installed:
* docker
* docker-compose https://docs.docker.com/compose/
* jdk8
* maven

### Build

#### Build the Microservice jars
```
~/COPCloudELK$ cd sensor/
~/COPCloudELK/sensor$ mvn package
~/COPCloudELK/sensor$ cd ../aggregator/
~/COPCloudELK/aggregator$ mvn package
~/COPCloudELK/aggregator$ cd ../controlUnit/
~/COPCloudELK/controlUnit$ mvn package
```

#### Build the Docker Images

```
~/COPCloudELK$ docker-compose -f docker-compose.yml -f extensions/logspout/logspout-compose.yml build
```

#### Run It

```
~/COPCloudELK$ docker-compose -f docker-compose.yml -f extensions/logspout/logspout-compose.yml up
```
On first run, Kibana will need a couple of minutes to "optimize and cache bundles". It will log a second line when it's ready.


#### Stop It

```
~/COPCloudELK$ docker-compose -f docker-compose.yml -f extensions/logspout/logspout-compose.yml stop
```

#### Useful URLS

```
KIBANA => localhost:5601
Frontend => Open ./frondend/index.html in your Browser
Sensor => localhost:8080/sensors localhost:8080/sensor/<SensorID>
Aggregator => localhost:8081/aggregate?sensorId=<sensorID>
ControlUnit => localhost:8082/data
```

### Usage
