package com.unam.proyecto1.repositorio;

import com.unam.proyecto1.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
    boolean existsUsuarioByEmail(String email);
    Usuario findByEmail(String email);
    Usuario findById(int id);
    /*Consulta todos los competidores asociados a un entrenador por el id del entrenador*/
    @Query(nativeQuery = true, value = "select * from usuario where entrenador_Id = :id")
    List<Usuario> findCompetidoresRegistrados(@Param("id")Integer id);
    /*Cuenta el número de disciplinas distintas que tiene registrado un entrenador, entre sus competidores*/
    @Query(nativeQuery = true, value = "select count(distinct disciplina_id) from  ((disciplina join evento using(disciplina_Id)) join competidores_eventos using(evento_id)) join usuario using (usuario_id) where entrenador_id = :id")
    Integer cuentaEventosEntrenador(@Param("id")Integer id);


    /*
    SELECT  FK_OrgId, COUNT(DISTINCT FK_UserId)
    FROM    TableName
    GROUP   BY FK_OrgId*/
}
