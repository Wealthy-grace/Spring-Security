CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(255) NOT NULL UNIQUE,
                                     password VARCHAR(255) NOT NULL,
                                     enabled BOOLEAN DEFAULT TRUE,
                                     email VARCHAR(255),
                                     fullName VARCHAR(255),
                                     image VARCHAR(255),
                                     role ENUM('ROLE_ADMIN', 'ROLE_USER') NOT NULL,
                                     PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;