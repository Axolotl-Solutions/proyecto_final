package com.unam.proyecto1.controlador;

import com.unam.proyecto1.modelo.Calificacion;
import com.unam.proyecto1.modelo.Disciplina;
import com.unam.proyecto1.modelo.Evento;
import com.unam.proyecto1.modelo.Usuario;
import com.unam.proyecto1.repositorio.CalificacionRepositorio;
import com.unam.proyecto1.repositorio.EventoRepositorio;
import com.unam.proyecto1.repositorio.UsuarioRepositorio;
import com.unam.proyecto1.servicio.CalificacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/juez")
public class JuezControlador {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    EventoRepositorio eventoRepositorio;

    @Autowired
    CalificacionRepositorio calificacionRepositorio;

    @Autowired
    CalificacionServicio calificacionServicio;

    @GetMapping("/")
    public String perfil(Model model, Principal principal) {
        Usuario usuario = usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
        Disciplina disciplinaJuez = usuario.getDisciplinaJuez();
        List<Evento> eventos = eventoRepositorio.findEventosByDisciplina(disciplinaJuez.getDisciplina_Id());
        model.addAttribute("eventos", eventos);
        model.addAttribute("usuarioRepositorio", usuarioRepositorio);
        model.addAttribute("calificacionRepositorio", calificacionRepositorio);
        model.addAttribute("disciplina",disciplinaJuez);
        return "inicioJueces";
    }


    @GetMapping("editarCalificacion/{idCalificacion}")
    private String editarCalificacion(@PathVariable Integer idCalificacion, Principal principal, Model modelo) {
        Usuario usuario = usuarioRepositorio.findByEmail(principal.getName());
        Calificacion calificacion = calificacionRepositorio.getById(idCalificacion);
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("calificacion", calificacion);
        modelo.addAttribute("calificacionesRepositorio", calificacionRepositorio);
        return "editarCalificacionJuez";
    }

    @GetMapping("editaCalificacion/{idCalificacion}")
    private String editaCalificacion(@PathVariable Integer idCalificacion, HttpServletRequest request, Principal principal, Model modelo){
        Usuario usuario = usuarioRepositorio.findByEmail(principal.getName());
        modelo.addAttribute("usuario", usuario);
        Calificacion calificacionAntigua = calificacionRepositorio.getById(idCalificacion);
        calificacionAntigua.setComentario(request.getParameter("comentario"));
        calificacionAntigua.setPuntaje(Double.parseDouble(request.getParameter("puntaje")));
        calificacionServicio.actualizaCalificacion(calificacionAntigua);
        modelo.addAttribute("competidor",calificacionAntigua.getCompetidor());
        modelo.addAttribute("calificacion",calificacionAntigua);
        modelo.addAttribute("exito",true);
        return "editarCalificacionJuez";
    }

    @GetMapping("crearCalificacion/{idCompetidor}/{idEvento}")
    private String crearCalificacion(@PathVariable Integer idCompetidor, @PathVariable Integer idEvento, Principal principal, Model modelo) {
        Usuario usuario = usuarioRepositorio.findByEmail(principal.getName());
        Usuario competidor = usuarioRepositorio.getById(idCompetidor);
        Evento evento = eventoRepositorio.getById(idEvento);
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("competidor", competidor);
        modelo.addAttribute("evento", evento);
        modelo.addAttribute("calificacionesRepositorio", calificacionRepositorio);
        return "crearCalificacionJuez";
    }

    @GetMapping("creaCalificacion/{idCompetidor}/{idEvento}")
    private String creaCalificacion(HttpServletRequest request, @PathVariable Integer idCompetidor, @PathVariable Integer idEvento, Principal principal, Model modelo) {
        Usuario usuario = usuarioRepositorio.findByEmail(principal.getName());
        Usuario competidor = usuarioRepositorio.getById(idCompetidor);
        Evento evento = eventoRepositorio.getById(idEvento);
        String comentario = request.getParameter("comentario");
        double puntaje = Double.parseDouble(request.getParameter("puntaje"));
        Calificacion nuevaCalificacion = calificacionServicio.creaCalificacion(evento, evento.getDisciplina(), usuario, competidor, puntaje, comentario);
        modelo.addAttribute("competidor",nuevaCalificacion.getCompetidor());
        modelo.addAttribute("calificacion",nuevaCalificacion);
        modelo.addAttribute("usuario",usuario);
        modelo.addAttribute("exito",true);
        return "editarCalificacionJuez";
    }

    @GetMapping("/buscarEventos")
    public String buscarEventos(Model model, Principal principal) {
        Usuario usuario = usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
        Disciplina disciplinaJuez = usuario.getDisciplinaJuez();
        List<Evento> eventos = eventoRepositorio.findEventosByDisciplina(disciplinaJuez.getDisciplina_Id());
        model.addAttribute("eventos", eventos);
        model.addAttribute("usuarioRepositorio", usuarioRepositorio);
        model.addAttribute("calificacionRepositorio", calificacionRepositorio);
        model.addAttribute("disciplina",disciplinaJuez);
        return "buscarEventosJueces";
    }

    @GetMapping("/buscaEvento/{idEvento}")
    public String buscarCompetidores(@PathVariable Integer idEvento, Model model, Principal principal) {
        Usuario usuario = usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
        Disciplina disciplinaJuez = usuario.getDisciplinaJuez();
        Evento evento = eventoRepositorio.getById(idEvento);
        model.addAttribute("evento", evento);
        model.addAttribute("usuarioRepositorio", usuarioRepositorio);
        model.addAttribute("calificacionRepositorio", calificacionRepositorio);
        model.addAttribute("disciplina",disciplinaJuez);
        return "buscarCompetidoresJueces";
    }

}
