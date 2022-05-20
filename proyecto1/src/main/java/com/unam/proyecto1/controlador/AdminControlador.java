package com.unam.proyecto1.controlador;

import com.unam.proyecto1.modelo.Usuario;
import com.unam.proyecto1.repositorio.UsuarioRepositorio;
import com.unam.proyecto1.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admins")
public class AdminControlador {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public String findUsuarios(Model model) {
        List<Usuario> admins = usuarioRepositorio.findAll();
        model.addAttribute("admins", admins);
        return "admins";
    }


       @GetMapping("/admins")
    public String admins(){
        return "admins";
    }

    @GetMapping("/inicioAdmin")
    public String perfil(Model model,Principal principal){
              Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
        System.out.println(usuario.getNombre()+" "+usuario.getRol() + "paso por perfil");
        return "inicioAdmin";
    }

    @GetMapping("/buscarDisciplinas")
    public String buscar(){
        return "buscarDisciplinas";
    }
    @PostMapping("/creaDisciplina")
    public String crea(HttpServletRequest request, Model model) {
        /*Usuario usuario = usuarioServicio.creaUsuario(request.getParameter("email"),
                request.getParameter("password"),
                request.getParameter("nombre"),
                request.getParameter("apellido_p"),
                request.getParameter("apellido_m"));
        model.addAttribute("exito", usuario != null);
        return "registro";*/

        String disciplina = request.getParameter("disciplina");
        String categoria = request.getParameter("categoria");
        String fecha = request.getParameter("fecha");
        String nombre_evento = request.getParameter("nombre");
        System.out.println(disciplina +" "+categoria+" "+fecha+" "+ nombre_evento);
        return "admins";
    }

}
