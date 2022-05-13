DROP TABLE IF EXISTS Administrador;

CREATE TABLE IF NOT EXISTS Administrador (
    id_Administrador SERIAL PRIMARY KEY NOT NULL,
    nombre           VARCHAR(50) NOT NULL,
    apellido_P       VARCHAR(30) NOT NULL,
    apellido_M       VARCHAR(30) NOT NULL,
    email            VARCHAR(50) NOT NULL,
    password         VARCHAR(60) NOT NULL,
    rol              VARCHAR(20) DEFAULT 'ROLE_ADMIN'
);

DROP TABLE IF EXISTS Competidor;

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
    rol              VARCHAR(20) DEFAULT 'ROLE_COMPETIDOR'
);

DROP TABLE IF EXISTS Entrenador;

CREATE TABLE IF NOT EXISTS Entrenador (
    id_Entrenador    SERIAL PRIMARY KEY NOT NULL,
    nombre           VARCHAR(50) NOT NULL,
    apellido_P       VARCHAR(30) NOT NULL,
    apellido_M       VARCHAR(30) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    email            VARCHAR(50) NOT NULL,
    password         VARCHAR(60) NOT NULL,
    sexo             VARCHAR(20) NOT NULL,
    rol              VARCHAR(20) DEFAULT 'ROLE_ENTRENADOR'
);

DROP TABLE IF EXISTS Juez;

CREATE TABLE IF NOT EXISTS Juez (
    id_Juez          SERIAL PRIMARY KEY NOT NULL,
    nombre           VARCHAR(50) NOT NULL,
    apellido_P       VARCHAR(30) NOT NULL,
    apellido_M       VARCHAR(30) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    email            VARCHAR(50) NOT NULL,
    password         VARCHAR(60) NOT NULL,
    sexo             VARCHAR(20) NOT NULL,
    rol              VARCHAR(20) DEFAULT 'ROLE_JUEZ'
);
