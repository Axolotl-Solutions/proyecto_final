package com.unam.proyecto1.repositorio;

import com.unam.proyecto1.modelo.Evento;
import com.unam.proyecto1.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepositorio extends JpaRepository<Evento, Integer> {
    boolean existsEventoByNombre_EventoAndCategoriaAndRama(String nombre_evento, String categoria, String rama);
    Evento findByNombre_EventoAndCategoriaAndRama();
}
