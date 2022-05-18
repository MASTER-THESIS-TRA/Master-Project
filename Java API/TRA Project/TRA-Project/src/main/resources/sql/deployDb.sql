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