CREATE TABLE IF NOT EXISTS authorities (
                                           username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users(username),
    PRIMARY KEY (username, authority)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
