DROP TABLE IF EXISTS User_roles;
DROP TABLE IF EXISTS Roles;
DROP TABLE IF EXISTS Cards;
DROP TABLE IF EXISTS Clients;

CREATE TABLE Clients(
                        id_client SERIAL,
                        name VARCHAR(15),
                        surname VARCHAR(20),
                        patronymic VARCHAR(20),
                        date_of_birth DATE,
                        PRIMARY KEY(id_client)
);

INSERT INTO clients(name, surname, patronymic, date_of_birth) VALUES
                                                                  ('Daniil', 'Fomin', 'Alexandrovich', '03.06.1999'),
                                                                  ('Eva', 'Alexeeva', 'Sidorovna', '22.07.1999'),
                                                                  ('Roman', 'Sclerov', 'Ivanovich', '15.06.2001'),
                                                                  ('Anna', 'Karpova', 'Evgeevna', '15.09.1999'),
                                                                  ('Ivan', 'Girikov', 'Vitalevich', '12.10.2000'),
                                                                  ('Evgenii', 'Glagolev', 'Alexeevich', '06.09.1998');

CREATE TABLE Cards(
                      id_card SERIAL,
                      pin VARCHAR(80),
                      balance NUMERIC,
                      id_client INT NOT NULL,
                      FOREIGN KEY (id_client) REFERENCES Clients(id_client),
                      PRIMARY KEY(id_card)
);

CREATE TABLE roles(
                      id SERIAL,
                      name VARCHAR(50) NOT NULL,
                      PRIMARY KEY (id)
);

CREATE TABLE users_roles(
                            user_id BIGINT NOT NULL,
                            role_id INT NOT NULL,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES cards(id_card),
                            FOREIGN KEY (role_id) REFERENCES roles(id)
);

INSERT INTO roles (name)
VALUES ('ROLE_USER') , ('ROLE_ADMIN');

INSERT INTO cards(pin, balance, id_client)  VALUES
                                                ('$2a$12$DQ/BYETerfZuovrLq7nWaOdUtx8Or2Nn98vvRkgQtAPB2W5PMDDKO', 500.3, 1),
                                                ('$2a$12$DQ/BYETerfZuovrLq7nWaOdUtx8Or2Nn98vvRkgQtAPB2W5PMDDKO', 10000.5, 1),
                                                ('$2a$12$DQ/BYETerfZuovrLq7nWaOdUtx8Or2Nn98vvRkgQtAPB2W5PMDDKO', 1039.99, 2),
                                                ('$2a$12$DQ/BYETerfZuovrLq7nWaOdUtx8Or2Nn98vvRkgQtAPB2W5PMDDKO', 990.21, 3),
                                                ('$2a$12$DQ/BYETerfZuovrLq7nWaOdUtx8Or2Nn98vvRkgQtAPB2W5PMDDKO', 1220.13, 4),
                                                ('$2a$12$DQ/BYETerfZuovrLq7nWaOdUtx8Or2Nn98vvRkgQtAPB2W5PMDDKO', 89020.11, 5),
                                                ('$2a$12$DQ/BYETerfZuovrLq7nWaOdUtx8Or2Nn98vvRkgQtAPB2W5PMDDKO', 10.5, 6);

insert into users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1);