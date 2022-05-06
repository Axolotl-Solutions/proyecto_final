package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Evento;
import com.unam.proyecto1.repositorio.EventoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class EventoServicioImpl implements EventoServicio{

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Override
    public Evento creaEvento(String nombreEvento, String nombre_Disciplina, String rama, String categoria) {
        if(eventoRepositorio.existsEventoByNombreEventoAndCategoriaAndRama(nombreEvento, categoria, rama)){
            return null;
        }
        Evento nuevoEvento = new Evento();
        nuevoEvento.setNombreEvento(nombreEvento);
        nuevoEvento.setNombre_Disciplina(nombre_Disciplina);
        nuevoEvento.setCategoria(categoria);
        nuevoEvento.setRama(rama);
        nuevoEvento.setFecha(Date.valueOf(LocalDate.now()));
        return eventoRepositorio.save(nuevoEvento);
    }
}
