package com.unam.proyecto1.repositorio;

import com.unam.proyecto1.modelo.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepositorio extends JpaRepository<Rol, Integer> {
    Rol findByNombre(String nombre);
}
