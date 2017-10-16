# Project Title

Tasks:
* Build and Run everything, then open the index.html in a Browser and Kibana in another Tab.
* Add a visualisation for the Field "DurationMs" containing all 3 Microservices
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

Build the Microservices
```
~/COPCloudELK$ cd sensor/
~/COPCloudELK/sensor$ mvn package
~/COPCloudELK/sensor$ cd ../aggregator/
~/COPCloudELK/aggregator$ mvn package
~/COPCloudELK/aggregator$ cd ../controlUnit/
~/COPCloudELK/controlUnit$ mvn package
```

Build the Docker Images

```
~/COPCloudELK$ docker-compose -f docker-compose.yml -f extensions/logspout/logspout-compose.yml build
```

Run It

```
~/COPCloudELK$ docker-compose -f docker-compose.yml -f extensions/logspout/logspout-compose.yml up
```


Stop It

```
~/COPCloudELK$ docker-compose -f docker-compose.yml -f extensions/logspout/logspout-compose.yml stop
```
