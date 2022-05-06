package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Evento;

import java.sql.Date;

public interface EventoServicio {
    Evento creaEvento(String nombreEvento, String nombre_Disciplina,  String rama, String categoria, Date fecha);
}
