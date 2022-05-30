package com.unam.proyecto1.repositorio;

import com.unam.proyecto1.modelo.Calificacion;
import com.unam.proyecto1.modelo.Evento;
import com.unam.proyecto1.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalificacionRepositorio extends JpaRepository<Calificacion, Integer> {
    boolean existsCalificacionByEventoAndJuezAndCompetidor(Evento evento, Usuario juez, Usuario competidor);
    Calificacion findByEventoAndJuezAndCompetidor(Evento evento, Usuario juez, Usuario competidor);
}
