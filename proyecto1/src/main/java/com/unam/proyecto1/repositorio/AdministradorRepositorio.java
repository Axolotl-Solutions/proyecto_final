package com.unam.proyecto1.repositorio;

import com.unam.proyecto1.modelo.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepositorio extends JpaRepository<Administrador, Integer> {

    boolean existsAdministradorByNombre(String nombre);
    Administrador findByNombre(String nombre);
}
