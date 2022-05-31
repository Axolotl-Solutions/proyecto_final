package com.unam.proyecto1.repositorio;

import com.unam.proyecto1.modelo.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepositorio  extends JpaRepository<Disciplina, Integer> {
    Disciplina findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
