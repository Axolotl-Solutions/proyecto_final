package com.unam.proyecto1.repositorio;

import com.unam.proyecto1.modelo.Disciplina;
import com.unam.proyecto1.modelo.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepositorio extends JpaRepository<Evento, Integer> {
    boolean existsEventoByNombreEventoAndDisciplinaAndCategoriaAndRama(String nombreEvento, Disciplina disciplina, String categoria, String rama);
    Evento findByNombreEventoAndDisciplinaAndCategoriaAndRama(String nombreEvento, Disciplina disciplina, String categoria, String rama);
    Evento findById(int id);

    @Query(nativeQuery = true, value = "select * from evento where disciplina_Id = :id")
    List<Evento> findEventosByDisciplina(@Param("id")Integer id);
}
