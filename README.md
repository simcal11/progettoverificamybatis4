## Progetto Verifica MyBatis 4

# Git
1. Creazione di questa repository per il versionamento del codice per lo sviluppo del Progetto Verifica

# Docker
1. Creazione di un docker-compose in cui vengono definiti 2 container: il primo per l'hosting della webapp, realizzato con un Dockerfile a partire da una image Tomcat e dal file .war esportato
    1. Definizione di una sottorete 172.50.0.0 con cui il container tomcat si connette al container postgres
    1. Definizione di un volume in modo tale da mantere salvate le modifiche che ogni volta vengono effettuate al database
1. 

# Flyway
1. Versionamento del database con la creazione di 6 migration, 3 per la creazione delle tabelle studenti, corsi, corsi_studenti_iscrizioni e 3 per il loro popolamento
2. Definizione del file flyway.conf in cui si impostano le configurazioni baisilari come, url, schemas, user, password.

# Spring
1. Realizzazione di una webapp con Spring 3 e MyBatis
    1. Creazione del pom.xml per le dipendenze
    1. Creazione del file mybatis-config.xml per la configurazione di mybatis
    1. Creazione del file mvc-dispatcher-servlet.xml e web.xml per la configurazione di Spring
    1. Creazione del package model con Studente.java, Corso.java, CorsiStudentiIscrizioni.java
    1. Creazione del package dao con i mapper CorsoMapper.java/xml e StudenteMapper.java/xml
    1. Creazione del package controller per i controller di Studente e Corsi
    1. Realizzazione per entrambe le entità delle operazioni di base
        1. READ (GET) di studenti, corsi, studenti iscritti ad un corso, corsi freqeuntati da uno studente
        1. CREATE (POST) di uno studente, uno corso, assegnazione di uno studente ad un corso, assegnazione di un corso ad uno studente
        1. UPDATE (PUT) di uno studente, di un corso
        1. DELETE (DELETE) di uno studente, di un corso, di tutti gli studenti, di tutti i corsi, rimozione di uno studente ad un corso, rimozione di un corso ad uno studente
