package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Usuario;

import com.unam.proyecto1.modelo.Rol;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface UsuarioServicio {
    Usuario creaUsuario(String email, String password, String nombre, String apellido_p, String apellido_m);
    Usuario creaUsuarioEntrenador(String email, String password, String nombre, String apellido_p, String apellido_m);
    Usuario creaUsuarioCompetidor(String email, String password, String nombre, String apellido_p, String apellido_m,
                                  String sexo, Date fecha, int peso, int altura, String entrenador_email);
    Usuario creaUsuarioJuez(String email, String password, String nombre, String apellido_p, String apellido_m, String nombre_Disciplina_Juez);
    public void eliminarUsuario(Integer id);
    public Usuario actualizarUsuario(Usuario usuario);
}
