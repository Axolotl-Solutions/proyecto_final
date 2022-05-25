package com.unam.proyecto1.controlador;

import com.unam.proyecto1.modelo.Disciplina;
import com.unam.proyecto1.modelo.Evento;
import com.unam.proyecto1.modelo.Usuario;
import com.unam.proyecto1.repositorio.DisciplinaRepositorio;
import com.unam.proyecto1.repositorio.UsuarioRepositorio;
import com.unam.proyecto1.servicio.EventoServicio;
import com.unam.proyecto1.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/admins")
public class AdminControlador {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private DisciplinaRepositorio disciplinaRepositorio;

    @Autowired
    private EventoServicio eventoServicio;

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

        return "inicioAdmin";
    }

    @GetMapping("/buscarDisciplinas")
    public String buscar(){

        return "buscarDisciplinas";
    }

    @PostMapping("/creaEvento")
    public String crea(HttpServletRequest request, Model model, Principal principal) {
        String nombreEvento = request.getParameter("nombreEvento");
        String rama = request.getParameter("rama");
        String categoria = request.getParameter("categoria");
        String fechaString = request.getParameter("fecha");
        Date fecha = Date.valueOf(fechaString);
        String nombreDisciplina = request.getParameter("nombreDisciplina");
        Evento evento = eventoServicio.creaEvento(nombreEvento, nombreDisciplina, rama, categoria, fecha);
        model.addAttribute("exito", evento != null);
        Usuario usuarioActual =  usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario", usuarioActual);
        return "admins";
    }

}
