package com.unam.proyecto1.servicio.seguridad;

import com.unam.proyecto1.modelo.Competidor;
import com.unam.proyecto1.modelo.Usuario;
import com.unam.proyecto1.repositorio.CompetidorRepositorio;
import com.unam.proyecto1.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DetalleCompetidorServicioImpl implements DetalleCompetidorServicio, UserDetailsService {
    @Autowired
    CompetidorRepositorio competidorRepositorio;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Competidor competidorActivo = competidorRepositorio.findByEmail(email);
        if (competidorActivo == null) {
            throw new UsernameNotFoundException("credentials not found");
        }
        UserDetails usuarioDetails;
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(competidorActivo.getRol().toString());
        usuarioDetails = new User(competidorActivo.getEmail(), competidorActivo.getPassword(), Arrays.asList(grantedAuthority));
        return usuarioDetails;
    }
}
