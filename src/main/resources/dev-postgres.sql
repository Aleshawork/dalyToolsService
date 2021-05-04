
INSERT INTO roles (id, name) VALUES
(1, 'ROLE_PERSON'),
(2, 'ROLE_ADMIN');

INSERT INTO person (id, first_name, second_name,username, email, password,role_id) VALUES
(1, 'Alexey', 'Borisov', 'AlexeyBorisov', 'borisov_am@mail.ru', '$2a$10$GN2V5I/dZSrHtZLrwiovYeHPHgD7kSU30ToIogMQ/RKSt4uOlpp8y',1),
(2, 'Egor', 'Ivanov', 'IvanovY',  'ivanov@mail.ru', '$2y$12$6G7v5bApu2ZRH7W.HV8NHuJs0W4pDQ3xMmXZSW1k03Dfvzk78lkaO',2);




