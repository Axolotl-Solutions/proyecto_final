DROP TABLE IF EXISTS Administrador;
CREATE TABLE Administrador (
    id_Administrador SERIAL PRIMARY KEY,
    nombre           VARCHAR(50),
    apellido_P       VARCHAR(30),
    apellido_M       VARCHAR(30),
    email            VARCHAR(50),
    password         VARCHAR(50)
);