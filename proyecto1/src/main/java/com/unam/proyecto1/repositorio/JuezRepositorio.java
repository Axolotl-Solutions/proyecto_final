package com.unam.proyecto1.repositorio;

import com.unam.proyecto1.modelo.Juez;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuezRepositorio extends JpaRepository<Juez, Integer> {

    boolean existsJuezByNombreJuezAndCategoriaAndRama(String nombre_Juez, String categoria, String rama);
    Juez findByNombreJuezAndCategoriaAndRama(String nombreJuez, String categoria, String rama);    
}
