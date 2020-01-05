DELETE FROM user;
DELETE FROM user_roles;
DELETE FROM restaurant;
DELETE FROM lunch_menu;
DELETE FROM dish;
DELETE FROM vote;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO user (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001),
       ('ROLE_USER', 100001);

INSERT INTO restaurant (name)
VALUES ('Джумбус'),
       ('Сабор де ла Вида Ресторан'),
       ('Lure Oyster Bar'),
       ('WE Cidreria'),
       ('Megobari');

INSERT INTO lunch_menu (date, restaurant_id)
VALUES (TODAY(), '100002'),
       (TODAY(), '100003'),
       (TODAY(), '100004'),
       (TODAY(), '100005'),
       (TODAY(), '100006'),
       (DATEADD('DAY', -1, TODAY()), '100002'),
       (DATEADD('DAY', -1, TODAY()), '100003'),
       (DATEADD('DAY', -1, TODAY()), '100004'),
       (DATEADD('DAY', -1, TODAY()), '100005'),
       (DATEADD('DAY', -1, TODAY()), '100006');

INSERT INTO dish (name, price, lunchmenu_id)
VALUES ('Закуска «Джумбус»', 890, 100007),
       ('Суп из ягнёнка', 340, 100007),
       ('Панированный сыр  ', 380, 100007),
       ('Ассорти брускетт', 880, 100008),
       ('Салат с ростбифом', 1290, 100008),
       ('Розовая Джоли (Намибия)', 197, 100008),
       ('Устрица с сочным манго и соусом табаско', 430, 100009),
       ('Буйабес из рыбы и морегадов', 490, 100009),
       ('Утка с малиной', 150, 100010),
       ('Клаб-сендвич с курицей и беконом', 510, 100010),
       ('Аджапсандали', 450, 100011),
       ('Салат по-мегрельски', 490, 100011),
       ('Аласка чорба', 370, 100012),
       ('Сибас «на граделе»', 350, 100012),
       ('Ассорти мясных деликатесов', 980, 100013),
       ('Салат понаш с уткой и фуа гра', 1290, 100013),
       ('Вонголе в томатном соусе', 880, 100013),
       ('Касабланка (Марокко)', 210, 100014),
       ('Руккола с авокадо и аргентинскими креветками', 730, 100014),
       ('Лосось крем-чиз', 160, 100015),
       ('Клаб-сендвич с лососем', 490, 100015),
       ('Грибной крем-суп', 390, 100015),
       ('Сациви с курицей', 470, 100016);

INSERT INTO vote (user_id, lunchmenu_id)
VALUES (100000, 100007),
       (100001, 100008)