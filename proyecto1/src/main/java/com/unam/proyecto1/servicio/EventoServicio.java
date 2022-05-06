package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Evento;

import java.sql.Date;

public interface EventoServicio {
    Evento creaEvento(String nombre_Evento, String nombre_Disciplina,  String rama, String categoria);
}
