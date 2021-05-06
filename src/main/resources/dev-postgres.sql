
CREATE TABLE IF NOT EXISTS refresh_token(
                                            id bigint,
                                            refresh_token varchar(255)
);

CREATE TABLE IF NOT EXISTS roles(
                                    id bigint primary key,
                                    name varchar(255)
);


CREATE TABLE IF NOT EXISTS person(
                                     id serial primary key,
                                     activation_code varchar(255),
                                     data_creation_code date,
                                     email varchar(255),
                                     first_name varchar(255),
                                     password varchar(255),
                                     second_name varchar(255),
                                     username varchar(255),
                                     role_id int references roles(id)

);

CREATE TABLE IF NOT EXISTS date_task(
                                        id serial primary key,
                                        fr_id int references person(id),
                                        date date,
                                        kol_task int
);


CREATE TABLE IF NOT EXISTS day_task(
                                       fr_nom int references date_task(id),
                                       task varchar(250),
                                       priority int
);

INSERT INTO roles (id, name) VALUES
(1, 'ROLE_PERSON'),
(2, 'ROLE_ADMIN');

INSERT INTO person (id, first_name, second_name,username, email, password,role_id) VALUES
(1, 'Alexey', 'Borisov', 'AlexeyBorisov', 'borisov_am@mail.ru', '$2a$10$GN2V5I/dZSrHtZLrwiovYeHPHgD7kSU30ToIogMQ/RKSt4uOlpp8y',1),
(2, 'Egor', 'Ivanov', 'IvanovY',  'ivanov@mail.ru', '$2y$12$6G7v5bApu2ZRH7W.HV8NHuJs0W4pDQ3xMmXZSW1k03Dfvzk78lkaO',2);




