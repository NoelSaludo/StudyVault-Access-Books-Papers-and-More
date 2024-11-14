CREATE USER 'FinalProject'@'localhost' IDENTIFIED BY 'FinalProject123';
GRANT ALTER, CREATE,
    DELETE, DROP,
    INSERT, REFERENCES,
    SELECT,
    UPDATE
    ON *.*
    TO 'FinalProject'@'localhost';

CREATE DATABASE FinalProject;
USE FinalProject;

CREATE TABLE user_account
(
    id         INT          NOT NULL AUTO_INCREMENT,
    username   VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE material_table
(
    id                      INT AUTO_INCREMENT  NOT NULL,
    material_title          VARCHAR(255)        NOT NULL,
    material_author         VARCHAR(255)        NOT NULL,
    material_language       VARCHAR(255)        NOT NULL,
    material_url            VARCHAR(255) UNIQUE NOT NULL,
    material_published_date DATE                NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE book_table
(
    id          INT AUTO_INCREMENT  NOT NULL,
    material_id INT UNIQUE          NOT NULL,
    isbn        VARCHAR(255) UNIQUE NOT NULL,
    publisher   VARCHAR(255)        NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT book_table_ibfk_1
        FOREIGN KEY (material_id)
            REFERENCES material_table (id)
            ON DELETE CASCADE
);

CREATE TABLE paper_table
(
    id          INT AUTO_INCREMENT  NOT NULL,
    material_id INT UNIQUE          NOT NULL,
    isbn        VARCHAR(255) UNIQUE NOT NULL,
    publisher   VARCHAR(255)        NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT paper_table_ibfk_1
        FOREIGN KEY (material_id)
            REFERENCES material_table (id)
            ON DELETE CASCADE
);

CREATE TABLE favorites
(
    id          INT NOT NULL UNIQUE,
    user_id     INT NOT NULL,
    material_id INT NOT NULL UNIQUE,
    PRIMARY KEY (id),
    CONSTRAINT favorites_ibfk_1
        FOREIGN KEY (user_id) REFERENCES user_account (id),
    CONSTRAINT favorites_ibfk_2
        FOREIGN KEY (material_id) REFERENCES material_table (id)
            ON DELETE CASCADE
)
