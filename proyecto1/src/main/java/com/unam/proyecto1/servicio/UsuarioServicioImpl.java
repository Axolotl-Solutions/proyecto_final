package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Usuario;
import com.unam.proyecto1.modelo.Rol;
import com.unam.proyecto1.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;
@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Override
    public Usuario creaUsuario(String email, String password, String nombre, String apellido_p, String apellido_m){
        if (usuarioRepo.existsUsuarioByEmail(email)) {
            return null;
        }
        Rol rol= new Rol();
        rol.setRol_id(2);
        rol.setNombre("ENTRENADOR");
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido_P(apellido_p);
        nuevoUsuario.setApellido_M(apellido_m);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        nuevoUsuario.setPassword(passwordEncoder.encode(password));
        nuevoUsuario.setEnabled(1);
        nuevoUsuario.addRol(rol);
        nuevoUsuario.setPeso(0);
        nuevoUsuario.setAltura(0);
        nuevoUsuario.setSexo("n/a");
        nuevoUsuario.setFecha_nacimiento(null);


        return usuarioRepo.save(nuevoUsuario);
    }
}
