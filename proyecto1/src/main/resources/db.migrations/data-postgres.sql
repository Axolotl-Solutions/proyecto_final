-- Insertar roles
INSERT INTO Rol(nombre)
VALUES('ROLE_ADMIN');

INSERT INTO Rol(nombre)
VALUES('ROLE_ENTRENADOR');

INSERT INTO Rol(nombre)
VALUES('ROLE_COMPETIDOR');

INSERT INTO Rol(nombre)
VALUES('ROLE_JUEZ');

-- Insertar usuarios
INSERT INTO Usuario(nombre, apellido_P, apellido_M, email, password, enabled)
VALUES('Axel David', 'García', 'Beltrán', 'axelgarcia@ciencias.unam.mx', '$2a$10$vMZ76pgLOM4mnsK4OtzO6O.hNfEZU4xDhx.nqTuRyzC6NBItwGFe.', 1);

INSERT INTO Usuario(nombre, apellido_P, apellido_M, email, password, enabled)
VALUES('Entrenador', 'Prueba', 'de Entrenador', 'entrenador@ciencias.unam.mx', '$2a$10$vMZ76pgLOM4mnsK4OtzO6O.hNfEZU4xDhx.nqTuRyzC6NBItwGFe.', 1);

-- Relacionar usuarios con sus roles
INSERT INTO Usuarios_Roles(usuario_Id, rol_Id)
VALUES(1, 1);

INSERT INTO Usuarios_Roles(usuario_Id, rol_Id)
VALUES(2, 2);


/*INSERT INTO Usuario(nombre, apellido_P, apellido_M, email, password, rol)
VALUES('Ian', 'García', 'V.', 'iangarcia@ciencias.unam.mx', '$2a$10$/OchO5I/34lLv55PxnbS3.7S9FPtqKUZfG.wIZxSBE1gkfHsO58Y.', 'ROLE_ADMIN');*/
