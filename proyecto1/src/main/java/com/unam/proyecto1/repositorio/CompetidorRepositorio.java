package com.unam.proyecto1.repositorio;

import com.unam.proyecto1.modelo.Competidor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompetidorRepositorio extends JpaRepository<Competidor, Integer> {
    boolean existsCompetidorByEmail(String email);
    Competidor findByEmail(String email);
    Optional<Competidor> findById(Integer id);
}
