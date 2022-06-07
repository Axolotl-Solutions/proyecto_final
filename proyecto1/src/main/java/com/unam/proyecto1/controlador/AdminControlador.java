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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.Date;
import java.util.List;
import java.util.Set;

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
    public String admins() {
        return "admins";
    }

    @GetMapping("/inicioAdmin")
    public String perfil(Model model, Principal principal) {

        return "inicioAdmin";
    }

    @GetMapping("/buscarDisciplinas")
    public String buscar() {

        return "buscarDisciplinas";
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
        model.addAttribute("exito", evento != null);
        Usuario usuarioActual = usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario", usuarioActual);
        return "admins";
    }

    @PostMapping("/creaJuez")
    public String creaJuez(HttpServletRequest request, Model model, Principal principal) {
        String emailJuez = request.getParameter("emailJuez");
        String passwordJuez = request.getParameter("passwordJuez");
        String nombreJuez = request.getParameter("nombreJuez");
        String apellidoPJuez = request.getParameter("apellidoPJuez");
        String apellidoMJuez = request.getParameter("apellidoMJuez");
        String nombreDisciplinaJuez = request.getParameter("nombreDisciplinaJuez");
        Usuario nuevoJuez = usuarioServicio.creaUsuarioJuez(emailJuez, passwordJuez, nombreJuez, apellidoPJuez, apellidoMJuez, nombreDisciplinaJuez);
        model.addAttribute("exito", nuevoJuez != null);
        Usuario usuarioActual = usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario", usuarioActual);
        return "admins";
    }

    @GetMapping("eliminarJuez/{idC}/{idE}")
    private String eliminarJuez(@PathVariable("idC") Integer idC, @PathVariable("idE") Integer idE) {
        Usuario juez = usuarioRepositorio.getById(idC);
        //Evento evento = eventoRepositorio.getById(idE);
        System.out.println(juez + " A eliminar");
        System.out.println(juez.getNombre() + " NOMBRE");
        System.out.println(juez.getEventos());
        juez.getEventos().remove(usuarioRepositorio.getById(idE));
        System.out.println(juez.getEventos());
        usuarioServicio.actualizarUsuario(juez);
        return "redirect:/admins/editar/" + juez.getEmail();
    }

    @GetMapping("/editarJuez/{email}")
    public String editaJuez(@PathVariable String email, @ModelAttribute Usuario juez,
                              HttpServletRequest request, Principal principal,
                              Model model) {
        List<Usuario> eventosAll = usuarioRepositorio.findAll();
        Usuario usuario = usuarioRepositorio.findByEmail(principal.getName());
        Usuario ju = usuarioRepositorio.findByEmail(email);
        Set<Evento> eventos = juez.getEventos();
        System.out.println(request.getParameter("email"));
        model.addAttribute("usuario", usuario);
        model.addAttribute("juez", ju);
        model.addAttribute("eventos", eventos);
        model.addAttribute("eventosAll", eventosAll);
        //model.addAttribute("calificacionRepositorio", ca);
        System.out.println("Entre aquí");
        return "inicioAdmin";
    }

    @RequestMapping("/buscar")
    public String busca(Model model, String error, Principal principal) {
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
        List<Usuario> usuarios = usuarioRepositorio.findCompetidoresRegistrados(usuario.getUsuario_Id());
        if (usuarios!=null)
            model.addAttribute("usuarios", usuarios);
        return "buscarJuez"; //buscarjuez
    }

    @GetMapping("eliminar/{id_Juez}")
    private String eliminar (@PathVariable("id_Juez") Integer id_Juez){
        usuarioServicio.eliminarUsuario(id_Juez);
        return "redirect:/juez/buscar";
    }

}
