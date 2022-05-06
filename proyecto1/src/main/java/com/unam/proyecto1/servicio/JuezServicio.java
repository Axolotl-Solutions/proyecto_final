package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Juez;

public interface JuezServicio {
    Juez creaJuez(String nombre_Juez, String nombre_Disciplina,  String rama, String categoria);
}
