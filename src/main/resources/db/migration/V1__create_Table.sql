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