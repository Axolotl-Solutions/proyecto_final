package com.unam.proyecto1.repositorio;

import com.unam.proyecto1.modelo.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepositorio extends JpaRepository<Evento, Integer> {
    boolean existsEventoByNombreEventoAndCategoriaAndRama(String nombreEvento, String categoria, String rama);
    Evento findByNombreEventoAndCategoriaAndRama(String nombreEvento, String categoria, String rama);
}
