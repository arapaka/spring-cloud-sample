docker service create --name kafka --publish 2181:2181 --publish 9092:9092 --env ADVERTISED_HOST=172.16.198.131 --env ADVERTISED_PORT=9092 --network danieloverlay spotify/kafka
