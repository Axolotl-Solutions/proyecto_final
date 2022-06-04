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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        List<Evento> eventos = eventoRepositorio.findAll();
        if (eventos.size()>0){
            return "redirect:/competidor/tabla/1";
        }
        List<Calificacion> cal = calificacionRepositorio.findAll();
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("eventos",eventos);
        return "Tabla";
    }

        @GetMapping("tabla/{id}")
    private String tabla(@PathVariable Integer id,Principal principal,Model modelo){
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        Evento e = eventoRepositorio.getById(id);
        List<Integer> idCompetidores=  calificacionRepositorio.getIdCompetidoresPuntaje(id);
        List<Usuario> competidores = new ArrayList<>();
        for (Integer i: idCompetidores) {
            competidores.add(usuarioRepositorio.getById(i));
        }
        Usuario primero,segundo,tercero = null;
        if (idCompetidores.size()>=3){
            Usuario usr = usuarioRepositorio.getById(idCompetidores.get(0));
            Usuario usr1 = usuarioRepositorio.getById(idCompetidores.get(1));
            Usuario usr2 = usuarioRepositorio.getById(idCompetidores.get(2));
            modelo.addAttribute("primero",
                    usr.getNombre() +" "+ usr.getApellido_P()+" "+usr.getApellido_M());
            modelo.addAttribute("segundo",
                    usr1.getNombre() +" "+ usr.getApellido_P()+" "+usr.getApellido_M());
            modelo.addAttribute("tercero",
                    usr2.getNombre() +" "+ usr.getApellido_P()+" "+usr.getApellido_M());
        }else if(idCompetidores.size()==2){
            Usuario usr = usuarioRepositorio.getById(idCompetidores.get(0));
            Usuario usr1 = usuarioRepositorio.getById(idCompetidores.get(1));
            modelo.addAttribute("primero",
                    usr.getNombre() +" "+ usr.getApellido_P()+" "+usr.getApellido_M());
            modelo.addAttribute("segundo",
                    usr1.getNombre() +" "+ usr.getApellido_P()+" "+usr.getApellido_M());
            modelo.addAttribute("tercero","N/E");
        }else if(idCompetidores.size()==1){
            Usuario usr = usuarioRepositorio.getById(idCompetidores.get(0));
            modelo.addAttribute("primero",
                    usr.getNombre() +" "+ usr.getApellido_P()+" "+usr.getApellido_M());
            modelo.addAttribute("segundo","N/E");
            modelo.addAttribute("tercero","N/E");
        }else{
            modelo.addAttribute("primero","N/E");
            modelo.addAttribute("segundo","N/E");
            modelo.addAttribute("tercero","N/E");
        }
        modelo.addAttribute("calificacionRepositorio",calificacionRepositorio);
        modelo.addAttribute("competidores",competidores);
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("evento",e);
        modelo.addAttribute("eventos",eventoRepositorio.findAll());
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
