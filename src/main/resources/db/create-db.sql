DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS lunch_menu;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS vote;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE user
(
    id         INTEGER   DEFAULT global_seq.nextval PRIMARY KEY,
    name       VARCHAR(255)            NOT NULL,
    email      VARCHAR                 NOT NULL,
    password   VARCHAR                 NOT NULL,
    registered TIMESTAMP DEFAULT now() NOT NULL,
    enabled    BOOL      DEFAULT TRUE  NOT NULL
);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
    id   INTEGER DEFAULT global_seq.nextval PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE lunch_menu
(
    id            INTEGER DEFAULT global_seq.nextval PRIMARY KEY,
    date          DATE    NOT NULL,
    restaurant_id INTEGER NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE dish
(
    id            INTEGER DEFAULT global_seq.nextval PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    price         BIGINT       NOT NULL,
    lunch_menu_id INTEGER      NOT NULL,
    FOREIGN KEY (lunch_menu_id) REFERENCES lunch_menu (id) ON DELETE CASCADE
);

CREATE TABLE vote
(
    id            INTEGER DEFAULT global_seq.nextval PRIMARY KEY,
    user_id       INTEGER NOT NULL,
    lunch_menu_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE,
    FOREIGN KEY (lunch_menu_id) REFERENCES lunch_menu (id) ON DELETE CASCADE
);