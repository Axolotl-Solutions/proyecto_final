

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
