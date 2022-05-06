package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Competidor;
import com.unam.proyecto1.repositorio.CompetidorRepositorio;
import com.unam.proyecto1.utils.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class CompetidorServicioImpl implements CompetidorServicio {
    @Autowired
    private CompetidorRepositorio competidorRepo;


    @Override
    public void eliminarCompetidor(Integer id) {
        competidorRepo.deleteById(id);
    }

    @Override
    public Competidor actualizarCompetidor(Competidor competidor) {
        return competidorRepo.save(competidor);
    }

    @Override
<<<<<<< HEAD
    public Competidor getCompetidorCorreo(String correo) {
        return competidorRepo.findByEmail(correo);
    }

    @Override
    public Competidor getCompetidorId(Integer id) {
        return competidorRepo.findById(id).get();
    }


    public Competidor creaCompetidor(String email, String password, String nombre, String apellido_p, String apellido_m, int peso, int altura, Date fecha_nacimiento, String sexo) {
=======
    public Competidor creaCompetidor(String email, String password, String nombre, String apellido_p, String apellido_m,
            int peso, int altura, Date fecha_nacimiento, String sexo) {
>>>>>>> 57b46358c89d5aed880b154d8a4422b856b4d0ac
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
        nuevoCompetidor.setRol(Rol.ROLE_COMPETITOR);
        return competidorRepo.save(nuevoCompetidor);
    }
}
