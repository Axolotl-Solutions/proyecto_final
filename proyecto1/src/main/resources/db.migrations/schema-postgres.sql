DROP TABLE IF EXISTS Usuario;
CREATE TABLE Usuario (
    id_Usuario       SERIAL PRIMARY KEY,
    nombre           VARCHAR(50),
    apellido_P       VARCHAR(30),
    apellido_M       VARCHAR(30),
    email            VARCHAR(50),
    password         VARCHAR(50),
    rol              VARCHAR(20) DEFAULT 'ROL_USER'
);