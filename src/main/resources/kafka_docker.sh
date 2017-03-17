

docker service create --name kafka --publish 2181:2181 --publish 9092:9092 --env ADVERTISED_HOST=172.16.198.131 --env ADVERTISED_PORT=9092 --network danieloverlay spotify/kafka


####  Ceate Kafka topics inside the Kafka Zookeper server
####  docker exec into spotify/kafka container

$KAFKA_HOME/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic helloworld
