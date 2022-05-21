CREATE TABLE if not exists AGENT (
    AGENT_ID char(36) PRIMARY KEY,
    NAME char(100) NOT NULL,
    EMAIL char(100) NOT NULL,
    PASSWORD char(100) not null
);
CREATE TABLE if not exists TRANSFERS (
    SENDER_ID char(50) NOT NULL,
    RECEIVER_ID char(50) NOT NULL
);
CREATE TABLE if not exists RESOURCE_TYPE (
       RESOURCE_TYPE_ID char(36) PRIMARY KEY,
       NAME char(100) NOT NULL,
       WEIGHT char(100) NOT NULL
);
CREATE TABLE IF NOT EXISTS EVENT(
    EVENT_ID char(36) PRIMARY KEY,
    EVENT_TYPE char(255) NOT NULL,
    AGENT_ID char(36) NOT NULL,
    TIME time NOT NULL,
    BODY char(5000)
);
CREATE TABLE IF NOT EXISTS OWNERSHIPS(
    AGENT_ID CHAR(36) NOT NULL,
    RESOURCE_TYPE CHAR(100) NOT NULL,
    AMOUNT INT NOT NULL,
    primary key (AGENT_ID,RESOURCE_TYPE)
);
CREATE TABLE IF NOT EXISTS CREDIT(
    AGENT_ID CHAR(36) NOT NULL,
    RESOURCE_TYPE CHAR(100) NOT NULL,
    AMOUNT INT NOT NULL,
    primary key (AGENT_ID,RESOURCE_TYPE)
);
CREATE TABLE IF NOT EXISTS TRANSFORM(
    TRANSFORM_ID CHAR(36) PRIMARY KEY,
    NAME CHAR(255) NOT NULL,
    TRANSFORM CHAR(1000) NOT NULL
);
CREATE TABLE IF NOT EXISTS LOCATION(
    LOCATION_ID CHAR(36) PRIMARY KEY,
    NAME TEXT NOT NULL,
    LONGITUDE NUMERIC NOT NULL,
    LATITUDE NUMERIC NOT NULL
);
create table if not exists WEIGHTS(
    TYPE CHAR(255) primary key,
    WEIGHT int not null
);