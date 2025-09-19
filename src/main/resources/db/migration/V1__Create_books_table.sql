CREATE TABLE IF NOT EXISTS books (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     title VARCHAR(255) NOT NULL,
                                     author VARCHAR(255) NOT NULL,
                                     isbn VARCHAR(255) UNIQUE NOT NULL,
                                     category ENUM('BIOGRAPHY', 'HISTORY', 'MYSTERY', 'PROGRAMMING', 'ROMANCE', 'SCIENCE_FICTION', 'TECHNOLOGY'),
                                     description VARCHAR(255),
                                     image VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
