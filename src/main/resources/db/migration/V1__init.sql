create table school (
    ID SERIAL PRIMARY KEY,
    short_name VARCHAR(30) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL
);