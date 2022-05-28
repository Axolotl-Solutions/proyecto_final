package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Disciplina;
import com.unam.proyecto1.modelo.Evento;
import com.unam.proyecto1.repositorio.DisciplinaRepositorio;
import com.unam.proyecto1.repositorio.EventoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class EventoServicioImpl implements EventoServicio{

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Autowired
    private DisciplinaRepositorio disciplinaRepositorio;

    @Override
    public Evento creaEvento(String nombreEvento, String nombreDisciplina, String rama, String categoria, Date fecha) {
        Disciplina disciplina = disciplinaRepositorio.findByNombre(nombreDisciplina);
        if(eventoRepositorio.existsEventoByNombreEventoAndDisciplinaAndCategoriaAndRama(nombreEvento, disciplina, categoria, rama)){
            return null;
        }
        Evento nuevoEvento = new Evento();
        nuevoEvento.setNombreEvento(nombreEvento);
        nuevoEvento.setDisciplina(disciplina);
        nuevoEvento.setCategoria(categoria);
        nuevoEvento.setRama(rama);
        nuevoEvento.setFecha(fecha);
        return eventoRepositorio.save(nuevoEvento);
    }
}
