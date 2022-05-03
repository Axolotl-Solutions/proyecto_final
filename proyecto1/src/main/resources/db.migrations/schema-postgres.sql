DROP TABLE IF EXISTS Usuario;
CREATE TABLE Usuario (
    id_Usuario       SERIAL PRIMARY KEY,
    nombre           VARCHAR(50),
    apellido_P       VARCHAR(30),
    apellido_M       VARCHAR(30),
    email            VARCHAR(50),
    password         VARCHAR(60)
);
ALTER TABLE Usuario
ADD COLUMN rol VARCHAR(10) DEFAULT 'ROLE_USER';