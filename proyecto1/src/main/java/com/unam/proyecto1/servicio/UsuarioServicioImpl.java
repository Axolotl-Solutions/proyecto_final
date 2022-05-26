package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Usuario;
import com.unam.proyecto1.modelo.Rol;
import com.unam.proyecto1.repositorio.RolRepositorio;
import com.unam.proyecto1.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Override
    public Usuario creaUsuario(String email, String password, String nombre, String apellido_p, String apellido_m){
        if (usuarioRepo.existsUsuarioByEmail(email)) {
            return null;
        }
        Rol rol = rolRepositorio.findByNombre("ROLE_ENTRENADOR");
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
    @Override
    public Usuario creaUsuarioCompetidor(String email, String password, String nombre,
                                         String apellido_p, String apellido_m, String sexo, Date fecha,
                                         int peso, int altura){
        if (usuarioRepo.existsUsuarioByEmail(email)) {
            return null;
        }
        Rol rol = rolRepositorio.findByNombre("ROLE_COMPETIDOR");
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido_P(apellido_p);
        nuevoUsuario.setApellido_M(apellido_m);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        nuevoUsuario.setPassword(passwordEncoder.encode(password));
        nuevoUsuario.setEnabled(1);
        nuevoUsuario.addRol(rol);
        nuevoUsuario.setPeso(peso);
        nuevoUsuario.setAltura(altura);
        nuevoUsuario.setSexo(sexo);
        nuevoUsuario.setFecha_nacimiento(fecha);
        return usuarioRepo.save(nuevoUsuario);
    }
    @Override
    public void eliminarUsuario(Integer id){
        usuarioRepo.deleteById(id);
    }
    @Override
    public Usuario actualizarUsuario(Usuario competidor) {
        return usuarioRepo.save(competidor);
    }
}
