CREATE TABLE IF NOT EXISTS english_words
(
  english_id INT AUTO_INCREMENT PRIMARY KEY ,
  english_word VARCHAR(150) NOT NULL UNIQUE
)
;

CREATE TABLE IF NOT EXISTS russian_words
(
  russian_id INT AUTO_INCREMENT PRIMARY KEY ,
  russian_word VARCHAR(150) NOT NULL UNIQUE
)
;
CREATE TABLE IF NOT EXISTS words_collections
(
  collection_id INT AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(150) NOT NULL
)
;

CREATE TABLE IF NOT EXISTS role
(
  role_id INT AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(150) NOT NULL UNIQUE
)
;

CREATE TABLE IF NOT EXISTS users
(
  user_id INT AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(255) NOT NULL ,
  login VARCHAR(20) NOT NULL UNIQUE ,
  password VARCHAR(20) NOT NULL ,
  email VARCHAR(64) NOT NULL UNIQUE ,
  role_id INT REFERENCES role(role_id)
)
;

CREATE TABLE IF NOT EXISTS words
(
  user_id INT REFERENCES users(user_id),
  english_id INT REFERENCES english_words(english_id),
  collection_id INT REFERENCES words_collections(collection_id),
  number_answers INT,
  correct_answers INT,
  russian_id INT REFERENCES russian_words(russian_id) ,
  constraint words_pkey
  primary key (user_id, english_id, russian_id)
)
;

INSERT INTO role (name) VALUES ('Administrator');
INSERT INTO role (name) VALUES ('Moderator');
INSERT INTO role (name) VALUES ('User');

INSERT INTO words_collections (name) VALUES ('public');
INSERT INTO words_collections (name) VALUES ('friendly');
INSERT INTO words_collections (name) VALUES ('private');

INSERT INTO users(name, login, password, email, role_id) VALUES ('admin', 'admin', 'admin', 'admin@adminmail.ru', 1);