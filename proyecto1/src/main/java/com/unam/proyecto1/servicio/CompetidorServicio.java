package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Competidor;

import java.sql.Date;

public interface CompetidorServicio {
    Competidor creaCompetidor(String email, String password, String nombre, String apellido_p,
                              String apellido_m, int peso, int altura, Date fecha_nacimiento,
                              String sexo);


    public void eliminarCompetidor(Integer id);
    public Competidor actualizarCompetidor(Competidor competidor);
    public Competidor getCompetidorCorreo(String correo);
    public Competidor getCompetidorId(Integer id);
}
