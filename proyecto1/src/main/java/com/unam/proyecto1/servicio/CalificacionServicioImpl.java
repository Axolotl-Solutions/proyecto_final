package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Calificacion;
import com.unam.proyecto1.modelo.Disciplina;
import com.unam.proyecto1.modelo.Evento;
import com.unam.proyecto1.modelo.Usuario;
import com.unam.proyecto1.repositorio.CalificacionRepositorio;
import com.unam.proyecto1.repositorio.DisciplinaRepositorio;
import com.unam.proyecto1.repositorio.EventoRepositorio;
import com.unam.proyecto1.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalificacionServicioImpl implements CalificacionServicio {

    @Autowired
    CalificacionRepositorio calificacionRepositorio;

    @Autowired
    EventoRepositorio eventoRepositorio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    DisciplinaRepositorio disciplinaRepositorio;

    @Override
    public Calificacion creaCalificacion(Evento evento,  Disciplina disciplina, Usuario juez, Usuario competidor, double puntaje, String comentario) {
        if(calificacionRepositorio.existsCalificacionByEventoAndJuezAndCompetidor(evento, juez, competidor)){
            return null;
        }

        Calificacion nuevaCalificacion = new Calificacion();
        nuevaCalificacion.setEvento(evento);
        nuevaCalificacion.setJuez(juez);
        nuevaCalificacion.setCompetidor(competidor);
        nuevaCalificacion.setPuntaje(puntaje);
        nuevaCalificacion.setComentario(comentario);
        return calificacionRepositorio.save(nuevaCalificacion);
    }

    @Override
    public Calificacion actualizaCalificacion(Calificacion calificacion){
        return calificacionRepositorio.save(calificacion);
    }
}
