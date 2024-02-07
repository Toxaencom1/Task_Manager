CREATE SCHEMA dz5db;

CREATE TABLE dz5db.tasks (
                                        id SERIAL PRIMARY KEY,
                                         description VARCHAR(255) NOT NULL,
                                         localDateTime TIMESTAMP,
                                         status VARCHAR(50)
);