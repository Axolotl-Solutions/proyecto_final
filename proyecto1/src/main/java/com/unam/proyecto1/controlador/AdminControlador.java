package com.unam.proyecto1.controlador;

import com.unam.proyecto1.modelo.Disciplina;
import com.unam.proyecto1.modelo.Evento;
import com.unam.proyecto1.modelo.Usuario;
import com.unam.proyecto1.repositorio.DisciplinaRepositorio;
import com.unam.proyecto1.repositorio.UsuarioRepositorio;
import com.unam.proyecto1.servicio.DisciplinaServicio;
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
    private DisciplinaServicio disciplinaServicio;

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
    @PostMapping("/creaDisciplina")
    public String creaDisciplina(HttpServletRequest request, Model model, Principal principal) {
        String nombreDisciplina = request.getParameter("nombreDisciplina");
        Disciplina disciplina = disciplinaServicio.creaDisciplina(nombreDisciplina);
        if (disciplina==null)
            model.addAttribute("errorDisciplina", true);
        else
            model.addAttribute("exito", true);
        Usuario usuarioActual =  usuarioRepositorio.findByEmail(principal.getName());
        List<Disciplina> disciplinas = disciplinaRepositorio.findAll();
        model.addAttribute("disciplinas",disciplinas);
        model.addAttribute("usuario", usuarioActual);
        return "admins";
    }
    @PostMapping("/creaEvento")
    public String creaEvento(HttpServletRequest request, Model model, Principal principal) {
        String nombreEvento = request.getParameter("nombreEvento");
        String rama = request.getParameter("rama");
        String categoria = request.getParameter("categoria");
        String fechaString = request.getParameter("fecha");
        Date fecha = Date.valueOf(fechaString);
        String nombreDisciplina = request.getParameter("nombreDisciplina");
        Evento evento = eventoServicio.creaEvento(nombreEvento, nombreDisciplina, rama, categoria, fecha);
        model.addAttribute("exito", true);
        Usuario usuarioActual =  usuarioRepositorio.findByEmail(principal.getName());
        List<Disciplina> disciplinas = disciplinaRepositorio.findAll();
        model.addAttribute("disciplinas",disciplinas);
        model.addAttribute("usuario", usuarioActual);
        return "admins";
    }

    @PostMapping("/creaJuez")
    public String creaJuez(HttpServletRequest request, Model model, Principal principal){
        String emailJuez = request.getParameter("emailJuez");
        String passwordJuez = request.getParameter("passwordJuez");
        String nombreJuez = request.getParameter("nombreJuez");
        String apellidoPJuez = request.getParameter("apellidoPJuez");
        String apellidoMJuez = request.getParameter("apellidoMJuez");
        String nombreDisciplinaJuez = request.getParameter("nombreDisciplinaJuez");
        Usuario nuevoJuez = usuarioServicio.creaUsuarioJuez(emailJuez, passwordJuez, nombreJuez, apellidoPJuez, apellidoMJuez, nombreDisciplinaJuez);
        if(nuevoJuez==null)
            model.addAttribute("error", true);
        else
            model.addAttribute("exito",true);
        Usuario usuarioActual =  usuarioRepositorio.findByEmail(principal.getName());
        List<Disciplina> disciplinas = disciplinaRepositorio.findAll();
        model.addAttribute("disciplinas",disciplinas);
        model.addAttribute("usuario", usuarioActual);
        return "admins";
    }

}
