package com.unam.proyecto1.controlador;

import com.unam.proyecto1.servicio.seguridad.DetalleUsuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                       Authentication authentication) throws ServletException, IOException {

        DetalleUsuario detalleUsuario = (DetalleUsuario) authentication.getPrincipal();

        String redirectUrl = request.getContextPath();

        if(detalleUsuario.hasRole("ROLE_ADMIN")) {
            redirectUrl = "inicioAdmin";
        } else if(detalleUsuario.hasRole("ROLE_ENTRENADOR")){
            redirectUrl = "entrenador/";
        } else if(detalleUsuario.hasRole("ROLE_COMPETIDOR")){
            redirectUrl = "competidor/";
        } else if(detalleUsuario.hasRole("ROLE_JUEZ")){
            redirectUrl = "usuarios/";
        }

        response.sendRedirect(redirectUrl);
    }
}
