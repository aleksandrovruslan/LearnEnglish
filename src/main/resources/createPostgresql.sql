create table IF NOT EXISTS english_words
(
  english_id serial not null
    constraint english_words_pkey
    primary key,
  english_word varchar(150) not null
    constraint english_words_english_word_key
    unique
)
;

create table IF NOT EXISTS russian_words
(
  russian_id serial not null
    constraint russian_words_pkey
    primary key,
  russian_word varchar(150) not null
    constraint russian_words_russian_word_key
    unique
)
;

create table IF NOT EXISTS words_collections
(
  collection_id serial not null
    constraint words_collections_pkey
    primary key,
  name varchar(150) not null
    constraint name_uniq
    unique
)
;

create table IF NOT EXISTS role
(
  role_id serial not null
    constraint role_pkey
    primary key,
  name varchar(150) not null
    CONSTRAINT role_name_key
    UNIQUE
)
;

create table IF NOT EXISTS users
(
  user_id serial not null
    constraint users_pkey
    primary key,
  name varchar(255) not null,
  login varchar(20)
    constraint users_login_key
    unique,
  password varchar(20) not null,
  email varchar(64)
    constraint users_email_key
    unique,
  role_id integer
    constraint chk_role
    references role
)
;

create table IF NOT EXISTS words
(
  user_id integer not null
    constraint answers_user_id_fkey
    references users,
  english_id integer not null
    constraint answers_english_id_fkey
    references english_words,
  collection_id integer
    constraint answers_collection_id_fkey
    references words_collections,
  number_answers integer,
  correct_answers integer,
  russian_id integer not null
    constraint words_russian_id_fkey
    references russian_words,
  constraint words_pkey
  primary key (user_id, english_id, russian_id)
)
;

INSERT INTO role (name) VALUES ('Administrator') ON CONFLICT DO NOTHING;
INSERT INTO role (name) VALUES ('Moderator') ON CONFLICT DO NOTHING;
INSERT INTO role (name) VALUES ('User') ON CONFLICT DO NOTHING;

INSERT INTO words_collections (name) VALUES ('public') ON CONFLICT DO NOTHING;
INSERT INTO words_collections (name) VALUES ('friendly') ON CONFLICT DO NOTHING;
INSERT INTO words_collections (name) VALUES ('private') ON CONFLICT DO NOTHING;

INSERT INTO users(name, login, password, email, role_id) VALUES ('admin', 'admin', 'admin', 'admin@adminmail.ru', 1) ON CONFLICT DO NOTHING;