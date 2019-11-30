DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS lunch_menu;
DROP TABLE IF EXISTS dish;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE restaurant
(
  id               INTEGER DEFAULT global_seq.nextval PRIMARY KEY,
  name             VARCHAR(255)            NOT NULL
);

CREATE TABLE lunch_menu
(
  id               INTEGER DEFAULT global_seq.nextval PRIMARY KEY,
  date             DATE               NOT NULL,
  restaurant_id    INTEGER                 NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE dish
(
  id               INTEGER DEFAULT global_seq.nextval PRIMARY KEY,
  name             VARCHAR(255)            NOT NULL,
  price            BIGINT                  NOT NULL,
  lunch_menu_id    INTEGER                 NOT NULL,
  FOREIGN KEY (lunch_menu_id) REFERENCES lunch_menu (id) ON DELETE CASCADE
);

CREATE TABLE user
(
    id               INTEGER DEFAULT global_seq.nextval PRIMARY KEY,
    name             VARCHAR(255)            NOT NULL
);