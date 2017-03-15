CREATE KEYSPACE IF NOT EXISTS sample WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
use sample;
CREATE TABLE IF NOT EXISTS environments (id TimeUUID PRIMARY KEY, name text, value text);
CREATE INDEX IF NOT EXISTS nameindex ON environments (name);
CREATE INDEX IF NOT EXISTS valueindex ON environments (value);
