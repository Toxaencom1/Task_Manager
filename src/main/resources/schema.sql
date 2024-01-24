CREATE TABLE IF NOT EXISTS tasks (
                                         id LONG AUTO_INCREMENT PRIMARY KEY,
                                         description VARCHAR(255) NOT NULL,
                                         localDateTime TIMESTAMP,
                                         status VARCHAR(50)
);