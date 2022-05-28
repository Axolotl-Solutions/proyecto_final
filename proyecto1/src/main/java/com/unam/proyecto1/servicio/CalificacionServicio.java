package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Calificacion;

public interface CalificacionServicio {
    Calificacion creaCalificacion(String nombreEvento, String nombreDisciplina, String categoria, String rama, String emailJuez, String emailCompetidor, int puntaje, String comentario);
}
