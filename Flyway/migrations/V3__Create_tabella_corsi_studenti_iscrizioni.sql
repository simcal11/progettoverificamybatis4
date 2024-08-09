CREATE TABLE corsi_studenti_iscrizioni (

    studenteid INTEGER REFERENCES studenti ON DELETE CASCADE,
    corsoid INTEGER REFERENCES corsi ON DELETE CASCADE,

    PRIMARY KEY(studenteid, corsoid)
);
