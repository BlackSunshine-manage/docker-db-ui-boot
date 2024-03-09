CREATE TABLE IF NOT EXISTS GREETINGS (
    id serial PRIMARY KEY,
    name varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS employee (
    id serial PRIMARY KEY,
    firstName varchar(50) NOT NULL,
    surname varchar(50) NOT NULL,
    age smallint
);
