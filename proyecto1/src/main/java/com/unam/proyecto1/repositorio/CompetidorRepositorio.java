package com.unam.proyecto1.repositorio;

import com.unam.proyecto1.modelo.Competidor;
import com.unam.proyecto1.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetidorRepositorio extends JpaRepository<Competidor, Integer> {
    boolean existsCompetidorByEmail(String email);
    Competidor findByEmail(String email);
}
