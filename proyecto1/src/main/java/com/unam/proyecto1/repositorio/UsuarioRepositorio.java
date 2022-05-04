package com.unam.proyecto1.repositorio;

import com.unam.proyecto1.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
    boolean existsUsuarioByEmail(String email);
    Usuario findByEmail(String email);
}