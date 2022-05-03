package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Usuario;

import java.util.List;

public interface UsuarioServicio {
    Usuario creaUsuario(String email, String password, String nombre, String apellido_p, String apellido_m);
}
