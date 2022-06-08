package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Calificacion;
import com.unam.proyecto1.modelo.Disciplina;
import com.unam.proyecto1.modelo.Evento;
import com.unam.proyecto1.modelo.Usuario;

public interface CalificacionServicio {
    Calificacion creaCalificacion(Evento evento, Disciplina disciplina, Usuario juez, Usuario competidor, double puntaje, String comentario);
    Calificacion actualizaCalificacion(Calificacion calificacion);
}
