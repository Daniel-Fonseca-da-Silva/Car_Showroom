CREATE DATABASE mydb;

CREATE TABLE car(id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    url_photo VARCHAR(255),
    url_video VARCHAR(255),
    latitude VARCHAR(255),
    longitude VARCHAR(255),
    category VARCHAR(255)
);
