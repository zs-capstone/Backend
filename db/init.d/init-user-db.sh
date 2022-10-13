#!/bin/bash
set -e

echo "START INIT-USER-DB";

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
  CREATE USER $APP_DB_USER WITH PASSWORD '$APP_DB_PASS';
  CREATE DATABASE $APP_DB_NAME;
  GRANT ALL PRIVILEGES ON DATABASE $APP_DB_NAME TO $APP_DB_USER;
  \connect $APP_DB_NAME $POSTGRES_USER

  DROP TABLE IF EXISTS eat;
  CREATE TABLE eat(
    id BIGINT,
    contentsid VARCHAR(50),
    title VARCHAR(50),
    roadaddress VARCHAR(500),
    tag VARCHAR(500),
    introduction VARCHAR(500),
    latitude double precision,
    longitude double precision,
    label VARCHAR(500),
    phoneno VARCHAR(500),
    imgpath VARCHAR(500),
    thumbnailpath VARCHAR(500),
    popularity double precision,
    tag_len integer,
    popular_tag VARCHAR(500),
    len INTEGER,
    primary key(id)
  );
  DROP TABLE IF EXISTS hotel;
  CREATE TABLE hotel(
    id BIGINT,
    contentsid VARCHAR(50),
    title VARCHAR(50),
    roadaddress VARCHAR(500),
    tag VARCHAR(500),
    introduction VARCHAR(500),
    latitude double precision,
    longitude double precision,
    label VARCHAR(500),
    phoneno VARCHAR(500),
    imgpath VARCHAR(500),
    thumbnailpath VARCHAR(500),
    popularity double precision,
    tag_len integer,
    popular_tag VARCHAR(500),
    len INTEGER,
    primary key(id)
  );
  DROP TABLE IF EXISTS place;
  CREATE TABLE place(
    id BIGINT,
    placeid INTEGER,
    title VARCHAR(50),
    address VARCHAR(500),
    introduction VARCHAR(500),
    latitude double precision,
    longitude double precision,
    dial_num VARCHAR(50),
    type VARCHAR(20),
    region1 VARCHAR(20),
    region2 VARCHAR(20),
    image_url VARCHAR(500),
    popularity double precision,
    nature INTEGER,
    outdoor INTEGER,
    fatigue INTEGER,
    sea INTEGER,
    walking INTEGER,
    exciting INTEGER,
    day INTEGER,
    culture INTEGER,
    group_num INTEGER,
    tag VARCHAR(500),
    primary key(id)
  );
  DROP TABLE IF EXISTS member;
  CREATE TABLE member(
    id SERIAL PRIMARY KEY,
    role VARCHAR(20) DEFAULT 'ROLE_USER',
    username VARCHAR(15) NOT NULL,
    nickname VARCHAR(15) NOT NULL,
    email VARCHAR(320) NOT NULL,
    password VARCHAR(100) NOT NULL,
    gender VARCHAR(1),
    year VARCHAR(4),
    month VARCHAR(2),
    day VARCHAR(2),
    region VARCHAR(255)
  );
  INSERT INTO member(role,username,nickname,email,password) VALUES ('ROLE_ADMIN','원우인','admin','idwooin@naver.com','admin1234');
  \dt
  \copy eat FROM './init_tables/eat.csv' DELIMITER ',' CSV HEADER;
  \copy hotel FROM './init_tables/hotel.csv' DELIMITER ',' CSV HEADER;
  \copy place FROM './init_tables/place.csv' DELIMITER ',' CSV HEADER;
  BEGIN;
    CREATE EXTENSION CUBE;
    CREATE EXTENSION EARTHDISTANCE;
  COMMIT;

EOSQL