package com.unam.proyecto1.controlador;

import com.unam.proyecto1.modelo.Calificacion;
import com.unam.proyecto1.modelo.Evento;
import com.unam.proyecto1.modelo.Usuario;
import com.unam.proyecto1.repositorio.CalificacionRepositorio;
import com.unam.proyecto1.repositorio.DisciplinaRepositorio;
import com.unam.proyecto1.repositorio.EventoRepositorio;
import com.unam.proyecto1.repositorio.UsuarioRepositorio;
import com.unam.proyecto1.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/competidor")
public class CompetidorControlador {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private DisciplinaRepositorio disciplinaRepositorio;
    @Autowired
    private EventoRepositorio eventoRepositorio;
    @Autowired
    private CalificacionRepositorio calificacionRepositorio;

    @GetMapping("/")
    public String perfil(Model model, Principal principal) {
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        int ndisciplinas = usuarioRepositorio.cuentaEventosCompetidor(usuario.getUsuario_Id());
        model.addAttribute("idcompetidor", usuario.getUsuario_Id());
        model.addAttribute("usuario", usuario);
        model.addAttribute("nDisciplinas",ndisciplinas);
        System.out.println("by competidor");
        return "inicioCompetidores";
    }

    @RequestMapping("/calificaciones")
    public String calificaciones(Model modelo, String error, Principal principal) {
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        List<Calificacion> calificaciones = calificacionRepositorio.findByCompetidor(usuario);
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("competidor",usuario);
        modelo.addAttribute("calificaciones",calificaciones);
        modelo.addAttribute("calificacionesRepositorio",calificacionRepositorio);
        return "calificacionCompetidor";
    }

    @RequestMapping("/tabla")
    public String tabla(Model modelo, String error, Principal principal) {
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        modelo.addAttribute("usuario", usuario);

        return "Tabla";
    }
    @GetMapping("calificacion/{email}")
    private String calificacion(@PathVariable String email,Principal principal,Model modelo){
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        Usuario usr = usuarioRepositorio.findByEmail(email);
        List<Calificacion> calificaciones = calificacionRepositorio.findByCompetidor(usr);
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("competidor",usr);
        modelo.addAttribute("calificaciones",calificaciones);
        modelo.addAttribute("calificacionesRepositorio",calificacionRepositorio);
        return "calificacionCompetidor";
    }
    @GetMapping("editar/{email}")
    private String editar(@PathVariable("email") String email, Model modelo,
                          Principal principal){
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        Usuario competidor =  usuarioRepositorio.findByEmail(email);
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("competidor",competidor);
        modelo.addAttribute("competidor",usuarioRepositorio.findByEmail(email));
        return "editarCompetidor";
    }

}
