package com.unam.proyecto1.repositorio;

import com.unam.proyecto1.modelo.Disciplina;
import com.unam.proyecto1.modelo.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepositorio extends JpaRepository<Evento, Integer> {
    boolean existsEventoByNombreEventoAndDisciplinaAndCategoriaAndRama(String nombreEvento, Disciplina disciplina, String categoria, String rama);
    Evento findByNombreEventoAndDisciplinaAndCategoriaAndRama(String nombreEvento, Disciplina disciplina, String categoria, String rama);
}
