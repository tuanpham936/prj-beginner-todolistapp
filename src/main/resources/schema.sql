create table todo (
    id VARCHAR(300) NOT NULL PRIMARY KEY,
    task VARCHAR(500) NOT NULL,
    exp DATE DEFAULT NULL
);

create table done (
    id VARCHAR(300) NOT NULL PRIMARY KEY,
    task VARCHAR(500) NOT NULL,
    exp DATE DEFAULT NULL
);

create table cancel (
    id VARCHAR(300) NOT NULL PRIMARY KEY,
    task VARCHAR(500) NOT NULL,
    exp DATE DEFAULT NULL
);