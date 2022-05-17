-- Una sola tabla de Usuario.
DROP TABLE IF EXISTS Usuario CASCADE;

CREATE TABLE IF NOT EXISTS Usuario (
    usuario_Id       SERIAL PRIMARY KEY NOT NULL,
    nombre           VARCHAR(50) NOT NULL,
    apellido_P       VARCHAR(30) NOT NULL,
    apellido_M       VARCHAR(30) NOT NULL,
    email            VARCHAR(50) NOT NULL UNIQUE,
    password         VARCHAR(64) NOT NULL,
    fecha_nacimiento DATE,
    sexo             VARCHAR(20),
    peso             INTEGER,
    altura           INTEGER,
    enabled 		 INTEGER DEFAULT NULL
);

-- Tabla de roles
DROP TABLE IF EXISTS Rol CASCADE;

CREATE TABLE IF NOT EXISTS Rol(
    rol_Id			SERIAL PRIMARY KEY NOT NULL,
    nombre 			VARCHAR(45) NOT NULL
);

-- Tabla que relaciona los usuarios con los roles
DROP TABLE IF EXISTS Usuarios_Roles;

CREATE TABLE IF NOT EXISTS Usuarios_Roles(
    usuario_Id 		INTEGER,
    rol_Id			INTEGER
);

ALTER TABLE Usuarios_Roles
    ADD CONSTRAINT usuarios_roles_pk
        PRIMARY KEY (usuario_Id, rol_Id);

ALTER TABLE Usuarios_Roles
    ADD CONSTRAINT usuario_fk
        FOREIGN KEY (usuario_Id)
            REFERENCES Usuario(usuario_id)
            ON UPDATE CASCADE
            ON DELETE CASCADE;

ALTER TABLE Usuarios_Roles
    ADD CONSTRAINT rol_fk
        FOREIGN KEY (rol_Id)
            REFERENCES Rol(rol_id)
            ON UPDATE CASCADE
            ON DELETE CASCADE;