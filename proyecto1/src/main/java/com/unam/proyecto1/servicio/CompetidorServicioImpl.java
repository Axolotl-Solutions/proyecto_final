package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Competidor;
import com.unam.proyecto1.repositorio.CompetidorRepositorio;
import com.unam.proyecto1.utils.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class CompetidorServicioImpl implements  CompetidorServicio{
    @Autowired
    private CompetidorRepositorio competidorRepo;

    @Override
    public Competidor creaCompetidor(String email, String password, String nombre, String apellido_p, String apellido_m, int peso, int altura, Date fecha_nacimiento, String sexo) {
        if (competidorRepo.existsCompetidorByEmail(email)) {
            return null;
        }
        Competidor nuevoCompetidor = new Competidor();
        nuevoCompetidor.setEmail(email);
        nuevoCompetidor.setNombre(nombre);
        nuevoCompetidor.setApellido_P(apellido_p);
        nuevoCompetidor.setApellido_M(apellido_m);
        nuevoCompetidor.setSexo(sexo);
        nuevoCompetidor.setAltura(altura);
        nuevoCompetidor.setPeso(peso);
        nuevoCompetidor.setFecha_nacimiento(fecha_nacimiento);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        nuevoCompetidor.setPassword(passwordEncoder.encode(password));
        nuevoCompetidor.setRol(Rol.ROLE_USUARIO);
        return competidorRepo.save(nuevoCompetidor);
    }
}
