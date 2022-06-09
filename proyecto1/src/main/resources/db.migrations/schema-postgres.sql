-- Una sola tabla de Usuario.
DROP TABLE IF EXISTS Usuario CASCADE;

CREATE TABLE IF NOT EXISTS Usuario (
    usuario_Id       SERIAL PRIMARY KEY NOT NULL,
    nombre           VARCHAR(50) NOT NULL,
    apellido_P       VARCHAR(30) NOT NULL,
    apellido_M       VARCHAR(30) NOT NULL,
    email            VARCHAR(50) NOT NULL UNIQUE,
    password         VARCHAR(64) NOT NULL,
    fecha_nacimiento DATE DEFAULT NULL,
    sexo             VARCHAR(20) DEFAULT NULL,
    peso             INTEGER DEFAULT NULL,
    altura           INTEGER DEFAULT NULL,
    entrenador_Id	 INTEGER DEFAULT NULL,
    disciplina_Juez  INTEGER DEFAULT NULL,
    enabled 		 INTEGER DEFAULT NULL,
    imagen           VARCHAR(300) DEFAULT 'user.svg'
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
    rol_Id			    INTEGER
);

ALTER TABLE Usuarios_Roles
    ADD CONSTRAINT usuarios_roles_pk
        PRIMARY KEY (usuario_Id, rol_Id);

ALTER TABLE Usuarios_Roles
    ADD CONSTRAINT usuario_fk
        FOREIGN KEY (usuario_Id)
            REFERENCES Usuario(usuario_Id)
            ON UPDATE CASCADE
            ON DELETE CASCADE;

ALTER TABLE Usuarios_Roles
    ADD CONSTRAINT rol_fk
        FOREIGN KEY (rol_Id)
            REFERENCES Rol(rol_Id)
            ON UPDATE CASCADE
            ON DELETE CASCADE;

-- Tabla de disciplinas
DROP TABLE IF EXISTS Disciplina CASCADE;

CREATE TABLE IF NOT EXISTS Disciplina(
    disciplina_Id	 SERIAL PRIMARY KEY NOT NULL,
    nombre 			 VARCHAR(30)
);

-- Tabla de eventos
DROP TABLE IF EXISTS Evento CASCADE;

CREATE TABLE IF NOT EXISTS Evento(
    evento_Id 			SERIAL PRIMARY KEY NOT NULL,
    nombre				VARCHAR(30) NOT NULL,
    disciplina_Id		INTEGER NOT NULL,
    rama				VARCHAR(10) NOT NULL,
    categoria			VARCHAR(30) NOT NULL,
    fecha				DATE
);

ALTER TABLE Evento
    ADD CONSTRAINT disciplina_fk
        FOREIGN KEY (disciplina_Id)
            REFERENCES Disciplina(disciplina_Id)
            ON UPDATE CASCADE
            ON DELETE CASCADE;

ALTER TABLE Evento
    ADD CONSTRAINT nombre_disciplina_rama_categoria_unique
        UNIQUE (nombre, disciplina_Id, rama, categoria);

-- Tabla que relaciona los competidores con los eventos
DROP TABLE IF EXISTS Competidores_Eventos;

CREATE TABLE IF NOT EXISTS Competidores_Eventos(
    usuario_Id 		INTEGER,
    evento_Id		INTEGER
);

ALTER TABLE Competidores_Eventos
    ADD CONSTRAINT competidores_eventos_pk
        PRIMARY KEY (usuario_Id, evento_Id);

ALTER TABLE Competidores_Eventos
    ADD CONSTRAINT usuario_fk
        FOREIGN KEY (usuario_Id)
            REFERENCES Usuario(usuario_Id)
            ON UPDATE CASCADE
            ON DELETE CASCADE;

ALTER TABLE Competidores_Eventos
    ADD CONSTRAINT evento_Id
        FOREIGN KEY (evento_Id)
            REFERENCES Evento(evento_Id)
            ON UPDATE CASCADE
            ON DELETE CASCADE;

-- Tabla de calificaciones
DROP TABLE IF EXISTS Calificacion;

CREATE TABLE IF NOT EXISTS Calificacion(
    calificacion_Id 	SERIAL PRIMARY KEY NOT NULL,
    evento_Id			INTEGER NOT NULL,
    juez_Id				INTEGER NOT NULL,
    competidor_Id		INTEGER NOT NULL,
    puntaje				DOUBLE PRECISION,
    comentario			VARCHAR(140)
);

ALTER TABLE Calificacion
    ADD CONSTRAINT evento_juez_competidor_unique
        UNIQUE (evento_Id, juez_Id, competidor_Id);

ALTER TABLE Calificacion
    ADD CONSTRAINT evento_fk
        FOREIGN KEY (evento_Id)
            REFERENCES Evento(evento_Id)
            ON UPDATE CASCADE
            ON DELETE CASCADE;

ALTER TABLE Calificacion
    ADD CONSTRAINT juez_fk
        FOREIGN KEY (juez_Id)
            REFERENCES Usuario(usuario_Id)
            ON UPDATE CASCADE
            ON DELETE CASCADE;

ALTER TABLE Calificacion
    ADD CONSTRAINT competidor_fk
        FOREIGN KEY (competidor_Id)
            REFERENCES Usuario(usuario_Id)
            ON UPDATE CASCADE
            ON DELETE CASCADE;
