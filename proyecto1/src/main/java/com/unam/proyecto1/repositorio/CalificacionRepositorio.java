package com.unam.proyecto1.repositorio;

import com.unam.proyecto1.modelo.Calificacion;
import com.unam.proyecto1.modelo.Evento;
import com.unam.proyecto1.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalificacionRepositorio extends JpaRepository<Calificacion, Integer> {
    boolean existsCalificacionByEventoAndJuezAndCompetidor(Evento evento, Usuario juez, Usuario competidor);
    Calificacion findByEventoAndJuezAndCompetidor(Evento evento, Usuario juez, Usuario competidor);
    List<Calificacion> findByCompetidor(Usuario competidor);
    @Query(nativeQuery = true, value = "select avg(puntaje) from calificacion where competidor_Id= :idu and evento_Id = :ide group by evento_id;")
    Integer promedioPorEvento(@Param("idu")Integer idu,@Param("ide")Integer ide);
}
