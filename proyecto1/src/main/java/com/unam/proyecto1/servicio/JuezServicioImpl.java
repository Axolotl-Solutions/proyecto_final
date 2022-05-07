package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Juez;
import com.unam.proyecto1.repositorio.JuezRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JuezServicioImpl implements JuezServicio {
    @Autowired
    private JuezRepositorio juezRepositorio;

    @Override
    public Juez creaJuez(String nombre_Juez, String nombre_Disciplina, String rama, String categoria) {
        /*if(juezRepositorio.existsJuezByNombreJuezAndCategoriaAndRama(nombre_Juez, categoria, rama)){
            return null;
        }*/
        Juez nuevoJuez = new Juez();
        nuevoJuez.setNombreJuez(nombre_Juez);
        nuevoJuez.setNombre_Disciplina(nombre_Disciplina);
        nuevoJuez.setCategoria(categoria);
        nuevoJuez.setRama(rama);
        return juezRepositorio.save(nuevoJuez);
    }
    
} 