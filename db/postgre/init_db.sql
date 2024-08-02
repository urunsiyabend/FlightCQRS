CREATE TABLE IF NOT EXISTS plane_type
(
    id           SERIAL PRIMARY KEY,
    manufacturer VARCHAR(255) NOT NULL,
    model        VARCHAR(255) NOT NULL,
    capacity     INTEGER      NOT NULL
);

CREATE TABLE IF NOT EXISTS plane
(
    id            SERIAL PRIMARY KEY,
    plane_type_id INTEGER REFERENCES plane_type (id),
    plane_number  VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS airport
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    city    VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS flight
(
    id                   SERIAL PRIMARY KEY,
    plane_id             INTEGER REFERENCES plane (id),
    departure_airport_id INTEGER REFERENCES airport (id),
    arrival_airport_id   INTEGER REFERENCES airport (id),
    departure_time       TIMESTAMP NOT NULL,
    arrival_time         TIMESTAMP NOT NULL
);
