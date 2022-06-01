-- Insertar roles
INSERT INTO Rol(nombre)
VALUES('ROLE_ADMIN');

INSERT INTO Rol(nombre)
VALUES('ROLE_ENTRENADOR');

INSERT INTO Rol(nombre)
VALUES('ROLE_COMPETIDOR');

INSERT INTO Rol(nombre)
VALUES('ROLE_JUEZ');

-- Insertar disciplinas
INSERT INTO Disciplina(nombre)
VALUES('Fútbol'),
      ('Tenis'),
      ('Basquetbol'),
      ('Voleibol'),
      ('Arquería'),
      ('Ajedrez'),
      ('Atletismo'),
      ('Gimnasia'),
      ('Natación'),
      ('Taekwondo');

-- Insertar usuarios
INSERT INTO Usuario(nombre, apellido_P, apellido_M, email, password, enabled)
VALUES('Axel David', 'García', 'Beltrán', 'axelgarcia@ciencias.unam.mx', '$2a$10$vMZ76pgLOM4mnsK4OtzO6O.hNfEZU4xDhx.nqTuRyzC6NBItwGFe.', 1);

INSERT INTO Usuario(nombre, apellido_P, apellido_M, email, password, enabled)
VALUES('Fernando Gerardo', 'Flores', 'García', 'entrenador@ciencias.unam.mx', '$2a$10$vMZ76pgLOM4mnsK4OtzO6O.hNfEZU4xDhx.nqTuRyzC6NBItwGFe.', 1);

INSERT INTO Usuario(nombre, apellido_P, apellido_M, email, password, fecha_nacimiento, sexo, peso, altura, entrenador_Id, enabled)
VALUES('Competidor', 'Prueba', 'de Competidor', 'competidor@ciencias.unam.mx', '$2a$10$vMZ76pgLOM4mnsK4OtzO6O.hNfEZU4xDhx.nqTuRyzC6NBItwGFe.', '2000-05-17', 'Masculino', 85, 180, 2, 1);

INSERT INTO Usuario(nombre, apellido_P, apellido_M, email, password, disciplina_juez, enabled)
VALUES('Adan', 'Ramirez', 'Mora', 'juez@ciencias.unam.mx', '$2a$10$vMZ76pgLOM4mnsK4OtzO6O.hNfEZU4xDhx.nqTuRyzC6NBItwGFe.', 7, 1);

INSERT INTO Usuario(nombre, apellido_P, apellido_M, email, password, disciplina_juez, enabled)
VALUES('German', 'Allende', 'Valderrama', 'german@ciencias.unam.mx', '$2a$10$vMZ76pgLOM4mnsK4OtzO6O.hNfEZU4xDhx.nqTuRyzC6NBItwGFe.', 5, 1);
-- Relacionar usuarios con sus roles
INSERT INTO Usuarios_Roles(usuario_Id, rol_Id)
VALUES(1, 1);

INSERT INTO Usuarios_Roles(usuario_Id, rol_Id)
VALUES(2, 2);

INSERT INTO Usuarios_Roles(usuario_Id, rol_Id)
VALUES(3, 3);

INSERT INTO Usuarios_Roles(usuario_Id, rol_Id)
VALUES(4, 4);

INSERT INTO Usuarios_Roles(usuario_Id, rol_Id)
VALUES(5, 4);

-- Insertar eventos, cada uno relacionado con su disciplina
INSERT INTO Evento(nombre, disciplina_Id, rama, categoria, fecha)
VALUES('100 metros planos', 7, 'Varonil', '18+ años', '2022-06-15'),
      ('100 metros planos', 7, 'Femenil', '18+ años', '2022-06-15'),
      ('Relevos', 7, 'Varonil', '18+ años', '2022-06-15'),
      ('Relevos', 7, 'Femenil', '18+ años', '2022-06-15'),
      ('Individual', 5, 'Varonil', '18+ años', '2022-06-18'),
      ('Individual', 5, 'Femenil', '18+ años', '2022-06-18'),
      ('Equipos', 5, 'Varonil', '18+ años', '2022-06-18'),
      ('Equipos', 5, 'Femenil', '18+ años', '2022-06-18');

-- Relacionar competidores con eventos
INSERT INTO Competidores_Eventos(usuario_Id, evento_Id)
VALUES(3, 1),
      (3, 3),
      (3, 5);

-- Crear calificaciones, relacionando jueces con eventos con competidores
INSERT INTO Calificacion(evento_Id, juez_Id, competidor_Id, puntaje, comentario)
VALUES(1, 4, 3, 8, 'Estuvo chido, pero no tanto, la neta padrino, tu puedes mejorar.'),
      (3, 4, 3, 10, 'Esta actuación fue lo más bonito que he visto en mi vida, ni cuando me casé me emocione tanto.'),
      (5, 5, 3, 2.5, 'Pésima actuación, si yo fuera tu no hubiese venido al chilaquildren'),
      (3, 5, 3, 9.5, 'Excepcional, pero te falta casí nada para que sea perfecto');

-- CONSULTAS DE PRUEBA
-- Eventos con sus disciplinas
SELECT Evento.nombre evento_Nombre, Disciplina.nombre disciplina_Nombre, rama, categoria
FROM Evento JOIN Disciplina USING(disciplina_Id);

-- Usuarios con sus roles
SELECT Usuario.nombre usuario_Nombre, Rol.nombre usuario_Nombre
FROM (Usuario NATURAL JOIN Usuarios_Roles) JOIN Rol USING(rol_Id);

-- Entrenadores con sus competidores
SELECT Entrenador.usuario_Id entrenador_Id, Entrenador.nombre entrenador_Nombre, Competidor.usuario_Id competidor_Id, Competidor.nombre competidor_Nombre
FROM Usuario AS Entrenador JOIN Usuario as Competidor ON Entrenador.usuario_Id = Competidor.entrenador_Id;

-- Competidores con los eventos en los que participarán
SELECT Competidor.usuario_Id competidor_Id, Competidor.nombre competidor_Nombre, Disciplinas_Eventos.evento_Id, Disciplinas_Eventos.evento_Nombre, Disciplinas_Eventos.disciplina_Nombre, Disciplinas_Eventos.rama, Disciplinas_Eventos.categoria, Disciplinas_Eventos.fecha
FROM (Usuario AS Competidor NATURAL JOIN Competidores_Eventos) JOIN (
    SELECT Evento.evento_id, Evento.nombre evento_Nombre, Disciplina.nombre disciplina_Nombre, Evento.rama, Evento.categoria, Evento.fecha
    FROM Evento JOIN Disciplina USING(disciplina_Id)
) AS Disciplinas_Eventos USING(evento_Id);

-- Calificaciones de cada juez en cada evento a cada competidor
SELECT *
FROM CALIFICACION;
