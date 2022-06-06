package com.unam.proyecto1.controlador;

import com.unam.proyecto1.modelo.Calificacion;
import com.unam.proyecto1.modelo.Disciplina;
import com.unam.proyecto1.modelo.Evento;
import com.unam.proyecto1.modelo.Usuario;
import com.unam.proyecto1.repositorio.CalificacionRepositorio;
import com.unam.proyecto1.repositorio.EventoRepositorio;
import com.unam.proyecto1.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/")
    public String perfil(Model model, Principal principal) {
        Usuario usuario = usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
        Disciplina disciplinaJuez = usuario.getDisciplinaJuez();
        List<Evento> eventos = eventoRepositorio.findEventosByDisciplina(disciplinaJuez.getDisciplina_Id());
        model.addAttribute("eventos", eventos);
        model.addAttribute("usuarioRepositorio", usuarioRepositorio);
        model.addAttribute("calificacionRepositorio", calificacionRepositorio);
        return "inicioJueces";
    }

    @GetMapping("calificacion/{idCalificacion}")
    private String calificacion(@PathVariable Integer idCalificacion, Principal principal, Model modelo) {

        Usuario usuario = usuarioRepositorio.findByEmail(principal.getName());
        Calificacion calificacion = calificacionRepositorio.getById(idCalificacion);

        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("calificacion", calificacion);
        modelo.addAttribute("calificacionesRepositorio", calificacionRepositorio);
        return "calificacionCompetidorJuez";
    }

    /*
    @GetMapping("editar/{email}")
    private String editar(@PathVariable("email") String email, Model modelo, Principal principal){
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        Usuario competidor =  usuarioRepositorio.findByEmail(email);
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("competidor",competidor);
        modelo.addAttribute("competidor",usuarioRepositorio.findByEmail(email));
        return "editarCalificacion";
    }

    @PostMapping("/edita/{id}")
    public String edita(@PathVariable Integer id, @ModelAttribute Usuario competidor, HttpServletRequest request, Principal principal, Model model){
        Usuario usuario = usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario",usuario);
        Usuario antiguo = usuarioRepositorio.getById(id);
        Usuario duplicado = usuarioRepositorio.findByEmail(competidor.getEmail());
        if(duplicado!= null && !duplicado.getEmail().equals(antiguo.getEmail())){
            model.addAttribute("error",true);
            model.addAttribute("competidor",antiguo);
            return "editarCalificacion";
        }
        antiguo.setEmail(competidor.getEmail());
        antiguo.setPeso(competidor.getPeso());
        antiguo.setAltura(competidor.getAltura());
        antiguo.setSexo(competidor.getSexo());
        Date date = Date.valueOf(request.getParameter("fecha"));
        antiguo.setFecha_nacimiento(date);
        antiguo.setNombre(competidor.getNombre());
        antiguo.setApellido_P(competidor.getApellido_P());
        antiguo.setApellido_M(competidor.getApellido_M());
        // usuarioServicio.actualizarUsuario(antiguo);
        model.addAttribute("exito",true);
        model.addAttribute("competidor",antiguo);
        return "editarCalificacion";
    }
    */

}
