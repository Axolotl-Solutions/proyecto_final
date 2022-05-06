package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Evento;
import com.unam.proyecto1.repositorio.EventoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.LocalDate;

public class EventoServicioImpl implements EventoServicio{

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Override
    public Evento creaEvento(String nombre_Evento, String nombre_Disciplina, String rama, String categoria) {
        if(eventoRepositorio.existsEventoByNombre_EventoAndCategoriaAndRama(nombre_Evento, categoria, rama)){
            return null;
        }
        Evento nuevoEvento = new Evento();
        nuevoEvento.setNombre_Evento(nombre_Evento);
        nuevoEvento.setNombre_Disciplina(nombre_Disciplina);
        nuevoEvento.setCategoria(categoria);,
        nuevoEvento.setRama(rama);
        nuevoEvento.setFecha(Date.valueOf(LocalDate.now()));
        return eventoRepositorio.save(nuevoEvento);
    }
}
