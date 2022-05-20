package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Usuario;

import com.unam.proyecto1.modelo.Rol;

import java.util.List;
import java.util.Set;

public interface UsuarioServicio {
    Usuario creaUsuario(String email, String password, String nombre, String apellido_p, String apellido_m);
}
