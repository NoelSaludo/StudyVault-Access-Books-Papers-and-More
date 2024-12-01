CREATE USER 'FinalProject'@'localhost' IDENTIFIED BY 'FinalProject123';
GRANT ALTER, CREATE,
    DELETE, DROP,
    INSERT, REFERENCES,
    SELECT,
    UPDATE
    ON FinalProject.*
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

CREATE TABLE admin
(
    id      INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL UNIQUE,
    PRIMARY KEY (id),
    CONSTRAINT admin_ibfk_1
        FOREIGN KEY (user_id) REFERENCES user_account (id)
            ON DELETE CASCADE
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
    book_id     INT AUTO_INCREMENT  NOT NULL,
    material_id INT UNIQUE          NOT NULL,
    isbn        VARCHAR(255) UNIQUE NOT NULL,
    publisher   VARCHAR(255)        NOT NULL,
    PRIMARY KEY (book_id),
    CONSTRAINT book_table_ibfk_1
        FOREIGN KEY (material_id)
            REFERENCES material_table (id)
            ON DELETE CASCADE
);

CREATE TABLE paper_table(
    paper_id    INT AUTO_INCREMENT  NOT NULL,
    material_id INT UNIQUE          NOT NULL,
    doi        VARCHAR(255) UNIQUE NOT NULL,
    journal_name   VARCHAR(255)        NOT NULL,
    PRIMARY KEY (paper_id),
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
        FOREIGN KEY (user_id) REFERENCES user_account (id)
            ON DELETE CASCADE,
    CONSTRAINT favorites_ibfk_2
        FOREIGN KEY (material_id) REFERENCES material_table (id)
            ON DELETE CASCADE
);

CREATE TABLE video_table
(
    video_id    INT AUTO_INCREMENT NOT NULL,
    material_id INT                NOT NULL UNIQUE,
    duration    INT                NOT NULL,
    Platform  VARCHAR(50)        NOT NULL,
    PRIMARY KEY (video_id),
    CONSTRAINT videos_ibfk_1
        FOREIGN KEY (material_id) REFERENCES material_table (id)
            ON DELETE CASCADE
);

CREATE TABLE seminar_table
(
    seminar_id  INT AUTO_INCREMENT NOT NULL,
    material_id INT                NOT NULL UNIQUE,
    type        ENUM ('ACADEMIC',
        'PROFFESSIONAL',
        'WEBINAR')                 NOT NULL,
    duration    INT                NOT NULL,
    PRIMARY KEY (seminar_id),
    CONSTRAINT seminar_ibfk_1
        FOREIGN KEY (material_id) REFERENCES material_table (id)
            ON DELETE CASCADE
);


INSERT INTO user_account
(username, password, first_name, last_name) VALUE
                                                ('admin', 'admin123', 'admin', 'admin');
INSERT INTO material_table
(id ,material_title, material_author, material_language, material_url, material_published_date)
VALUES
    (1,'Discrete Math With Coding', 'Hugo G. Junghem', 'english', 'https://libgen.is/book/index.php?md5=87EAAC01681670BCEFA28C2FE443E8B7', '2023-08-21'),
    (2,'Improving computer-marked assessment. What are the limits?', 'Sally Jordan', 'english', 'https://youtu.be/FEdEIi-uHYU?si=5vDBBJScncHbg9iO','2015-10-05'),
    (3, 'Contributing to success in an introductory computer science course: a study of twelve factors', 'Brenda Cantwell Wilson', 'english', 'https://dl.acm.org/doi/abs/10.1145/366413.364581', '2001-02-01'),
    (4, 'OpenGL Course - Create 3D and 2D Graphics With C++', 'Victor Gordan', 'englihs', 'https://www.youtube.com/watch?v=45MIykWJ-C4', '2021-04-27');

INSERT INTO book_table (book_id, material_id, isbn, publisher) VALUE
(1, 1, '	9781032398525', 'CRC Press');

INSERT INTO video_table (material_id, duration, Platform) VALUE
(4, 106, 'Youtube');

INSERT INTO paper_table (material_id, doi, journal_name) VALUE
(3, '10.1145/366413.364581', 'ACM SIGCSE Bulletin, Volume 33, Issue 1');

INSERT INTO seminar_table (material_id, type, duration) VALUE
    (2, 'ACADEMIC', 55);