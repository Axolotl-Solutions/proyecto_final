package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Evento;

public interface EventoServicio {
    Evento creaEvento(String nombreEvento, String nombre_Disciplina,  String rama, String categoria);
}
