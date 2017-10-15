docker-compose -f docker-compose.yml -f extensions/logspout/logspout-compose.yml up
docker-compose -f docker-compose.yml -f extensions/logspout/logspout-compose.yml build
docker-compose -f docker-compose.yml -f extensions/logspout/logspout-compose.yml stop
