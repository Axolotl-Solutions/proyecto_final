package com.unam.proyecto1.controlador;

import com.unam.proyecto1.modelo.Usuario;
import com.unam.proyecto1.repositorio.UsuarioRepositorio;
import com.unam.proyecto1.servicio.UsuarioServicio;
import com.unam.proyecto1.modelo.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    
    @GetMapping("/registro")
    public String registra(){
        return "registro";
    }


    @PostMapping("/crea")
    public String crea(HttpServletRequest request, Model model) {

                Usuario usuario = usuarioServicio.creaUsuario(request.getParameter("email"),
                    request.getParameter("password"),
                 request.getParameter("nombre"),
                request.getParameter("apellido_p"),
                request.getParameter("apellido_m")
                );


        model.addAttribute("exito", usuario != null);
        return "registro";
    }

}
