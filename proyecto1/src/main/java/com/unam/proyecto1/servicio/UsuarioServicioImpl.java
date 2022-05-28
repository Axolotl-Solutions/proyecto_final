package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Disciplina;
import com.unam.proyecto1.modelo.Usuario;
import com.unam.proyecto1.modelo.Rol;
import com.unam.proyecto1.repositorio.DisciplinaRepositorio;
import com.unam.proyecto1.repositorio.RolRepositorio;
import com.unam.proyecto1.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Autowired
    private DisciplinaRepositorio disciplinaRepositorio;

    @Override
    public Usuario creaUsuario(String email, String password, String nombre, String apellido_p, String apellido_m) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido_P(apellido_p);
        nuevoUsuario.setApellido_M(apellido_m);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        nuevoUsuario.setPassword(passwordEncoder.encode(password));
        nuevoUsuario.setEnabled(1);
        return nuevoUsuario;
    }

    @Override
    public Usuario creaUsuarioEntrenador(String email, String password, String nombre, String apellido_p, String apellido_m) {
        if (usuarioRepositorio.existsUsuarioByEmail(email)) {
            return null;
        }
        Rol rol = rolRepositorio.findByNombre("ROLE_ENTRENADOR");
        Usuario nuevoEntrenador = creaUsuario(email, password, nombre, apellido_p, apellido_m);;
        nuevoEntrenador.setPeso(0);
        nuevoEntrenador.setAltura(0);
        nuevoEntrenador.setSexo("n/a");
        nuevoEntrenador.setFecha_nacimiento(null);
        nuevoEntrenador.addRol(rol);
        return usuarioRepositorio.save(nuevoEntrenador);
    }

    @Override
    public Usuario creaUsuarioCompetidor(String email, String password, String nombre,
                                         String apellido_p, String apellido_m, String sexo, Date fecha_nac,
                                         int peso, int altura){
        if (usuarioRepositorio.existsUsuarioByEmail(email)) {
            return null;
        }
        Rol rol = rolRepositorio.findByNombre("ROLE_COMPETIDOR");
        Usuario nuevoCompetidor = creaUsuario(email, password, nombre, apellido_p, apellido_m);
        nuevoCompetidor.setPeso(peso);
        nuevoCompetidor.setAltura(altura);
        nuevoCompetidor.setSexo(sexo);
        nuevoCompetidor.setFecha_nacimiento(fecha_nac);
        nuevoCompetidor.addRol(rol);
        return usuarioRepositorio.save(nuevoCompetidor);
    }

    @Override
    public Usuario creaUsuarioJuez(String email, String password, String nombre, String apellido_p, String apellido_m, String nombreDisciplnaJuez) {
        if (usuarioRepositorio.existsUsuarioByEmail(email)) {
            return null;
        }
        Rol rol = rolRepositorio.findByNombre("ROLE_JUEZ");
        Disciplina discplinaJuez = disciplinaRepositorio.findByNombre(nombreDisciplnaJuez);
        Usuario nuevoJuez = creaUsuario(email, password, nombre, apellido_p, apellido_m);
        nuevoJuez.setDisciplinaJuez(discplinaJuez);
        nuevoJuez.addRol(rol);
        return usuarioRepositorio.save(nuevoJuez);
    }

    @Override
    public void eliminarUsuario(Integer id){
        usuarioRepositorio.deleteById(id);
    }

    @Override
    public Usuario actualizarUsuario(Usuario competidor) {
        return usuarioRepositorio.save(competidor);
    }
}
