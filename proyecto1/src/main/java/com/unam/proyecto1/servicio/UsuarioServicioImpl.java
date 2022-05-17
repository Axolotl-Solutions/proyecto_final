package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Usuario;
import com.unam.proyecto1.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Override
    public Usuario creaUsuario(String email, String password, String nombre, String apellido_p, String apellido_m){
        if (usuarioRepo.existsUsuarioByEmail(email)) {
            return null;
        }
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido_P(apellido_p);
        nuevoUsuario.setApellido_M(apellido_m);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        nuevoUsuario.setPassword(passwordEncoder.encode(password));
        return usuarioRepo.save(nuevoUsuario);
    }
}
