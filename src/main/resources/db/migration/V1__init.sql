create table school (
    ID SERIAL PRIMARY KEY,
    short_name VARCHAR(30) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL
);

create table teacher (
    ID SERIAL PRIMARY KEY,
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(60) NOT NULL,
    description TEXT,
    birth_date DATE,
    photo_file VARCHAR(80),
    school_id INT,
    CONSTRAINT fk_customer FOREIGN KEY(school_id) REFERENCES school(id)
);