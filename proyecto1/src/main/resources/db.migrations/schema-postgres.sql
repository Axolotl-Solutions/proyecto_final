CREATE TABLE IF NOT EXISTS Usuario (
    id_Usuario       SERIAL PRIMARY KEY NOT NULL,
    nombre           VARCHAR(50) NOT NULL,
    apellido_P       VARCHAR(30) NOT NULL,
    apellido_M       VARCHAR(30) NOT NULL,
    email            VARCHAR(50) NOT NULL,
    password         VARCHAR(60) NOT NULL
);
ALTER TABLE Usuario
ADD COLUMN IF NOT EXISTS rol VARCHAR(20) DEFAULT 'ROLE_USER';

CREATE TABLE IF NOT EXISTS Competidor (
    id_Competidor    SERIAL PRIMARY KEY NOT NULL,
    nombre           VARCHAR(50) NOT NULL,
    apellido_P       VARCHAR(30) NOT NULL,
    apellido_M       VARCHAR(30) NOT NULL,
    peso             INTEGER NOT NULL,
    altura           INTEGER NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    email            VARCHAR(50) NOT NULL,
    password         VARCHAR(60) NOT NULL,
    sexo             VARCHAR(20) NOT NULL,
    rol              VARCHAR(20) DEFAULT 'ROLE_COMPETITOR'
);


CREATE TABLE IF NOT EXISTS Evento (
    id_evento           SERIAL PRIMARY KEY NOT NULL,
    nombre_Evento       VARCHAR(50) NOT NULL,
    nombre_Disciplina   VARCHAR(50) NOT NULL,
    rama                VARCHAR(50) NOT NULL,
    categoria           VARCHAR(50) NOT NULL,
    fecha               DATE NOT NULL
);
