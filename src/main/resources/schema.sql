create table todo (
    id VARCHAR(300) NOT NULL PRIMARY KEY,
    task VARCHAR(500) NOT NULL,
    amount DATE NOT NULL
);

create table done (
    id VARCHAR(300) NOT NULL PRIMARY KEY,
    task VARCHAR(500) NOT NULL,
    amount DATE NOT NULL
);

create table cancel (
    id VARCHAR(300) NOT NULL PRIMARY KEY,
    task VARCHAR(500) NOT NULL,
    amount DATE NOT NULL
);