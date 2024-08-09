DROP TABLE IF EXISTS corsi_studenti_iscrizioni;

DROP TABLE IF EXISTS studenti;

DROP TABLE IF EXISTS corsi;

CREATE TABLE studenti (
    id SERIAL PRIMARY KEY,
    nome varchar(255),
    cognome varchar(255),
    matricola varchar(255),
    email varchar(255)
);
