DROP DATABASE IF EXISTS test_db;
CREATE DATABASE test_db;
USE test_db;
-- >>>>>>>>>>>> change this line so it uses your database, not mine <<<<<<<<<<<<<<<

DROP TABLE IF EXISTS metropolises;
-- remove table if it already exists and start from scratch

CREATE TABLE metropolises (
  metropolis CHAR(64),
  continent  CHAR(64),
  population BIGINT
);

INSERT INTO metropolises VALUES
  ("Mumbai", "Asia", 20400000),
  ("New York", "North America", 21295000),
  ("San Francisco", "North America", 5780000),
  ("London", "Europe", 8580000),
  ("Rome", "Europe", 2715000),
  ("Melbourne", "Australia", 3900000),
  ("San Jose", "North America", 7354555),
  ("Rostov-on-Don", "Europe", 1052000);

DROP TABLE IF EXISTS universities;
-- remove table if it already exists and start from scratch

CREATE TABLE universities (
  university CHAR(64),
  metropolis CHAR(64)
);

INSERT INTO universities VALUES
  ("Stanford University", "San Francisco"),
  ("Columbia University", "New York"),
  ("Juilliard School", "New York"),
  ("Fordham University", "New York"),
  ("Harvard", "Boston"),
  ("University of the Arts London", "London"),
  ("London School of Economics", "London"),
  ("University of the Arts", "London");
