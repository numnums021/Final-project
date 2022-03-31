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
                      pinCode INT NOT NULL,
                      balance NUMERIC,
                      id_client INT NOT NULL,
                      FOREIGN KEY (id_client) REFERENCES Clients(id_client),
                      PRIMARY KEY(id_card)
);

INSERT INTO cards(pinCode, balance, id_client)  VALUES
                                                    (0001, 500.3, 1),
                                                    (0010, 10000.5, 1),
                                                    (0011, 1039.99, 2),
                                                    (0100, 990.21, 3),
                                                    (0101, 1220.13, 4),
                                                    (0110, 89020.11, 5),
                                                    (0111, 10.5, 6);


SELECT name, surname, patronymic, date_of_birth, id_card, balance
FROM clients, cards
WHERE clients."id_client" = cards."id_client";
