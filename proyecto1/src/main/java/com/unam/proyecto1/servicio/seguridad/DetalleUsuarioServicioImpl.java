package com.unam.proyecto1.servicio.seguridad;

import com.unam.proyecto1.modelo.Usuario;
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
public class DetalleUsuarioServicioImpl implements DetalleUsuarioServicio, UserDetailsService {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuarioActivo = usuarioRepositorio.findByEmail(email);
        if (usuarioActivo == null) {
            throw new UsernameNotFoundException("credentials not found");
        }
        UserDetails usuarioDetails;
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(usuarioActivo.getRol().toString());
        usuarioDetails = new User(usuarioActivo.getEmail(), usuarioActivo.getPassword(), Arrays.asList(grantedAuthority));
        return usuarioDetails;
    }
}
