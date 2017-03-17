docker volume create cassandra

docker service create --name cassandra --mount type=volume,src=cassandra,dst=/var/lib/cassandra --publish 9042 --network danieloverlay cassandra