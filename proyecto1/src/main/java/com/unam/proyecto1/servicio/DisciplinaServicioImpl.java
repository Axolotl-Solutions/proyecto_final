package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Disciplina;
import com.unam.proyecto1.repositorio.DisciplinaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaServicioImpl implements DisciplinaServicio {

    @Autowired
    DisciplinaRepositorio disciplinaRepositorio;

    @Override
    public Disciplina creaDisciplina(String nombre) {
        if(disciplinaRepositorio.existsByNombre(nombre)){
            return null;
        }
        Disciplina nuevaDisciplina = new Disciplina();
        nuevaDisciplina.setNombre(nombre);
        return disciplinaRepositorio.save(nuevaDisciplina);
    }
}
