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
@RequestMapping("/entrenador")
public class EntrenadorControlador {
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
        List<Usuario> usuarios = usuarioRepositorio.findCompetidoresRegistrados(usuario.getUsuario_Id());
        int ndisciplinas = usuarioRepositorio.cuentaEventosEntrenador(usuario.getUsuario_Id());
        model.addAttribute("numCompetidores", usuarios.size());
        model.addAttribute("usuario", usuario);
        model.addAttribute("nDisciplinas",ndisciplinas);
        return "inicioEntrenador";
    }
    @GetMapping("/registrar")
    public String registrar(HttpServletRequest request,Model model, Principal principal) {
        List<Evento> eventos = eventoRepositorio.findAll();
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
        model.addAttribute("eventos", eventos);
        return "registraCompetidor";
    }
    @PostMapping("/edita/{id}")
    public String edita(@PathVariable Integer id,@ModelAttribute Usuario competidor,
                                       HttpServletRequest request,Principal principal,
                                        Model model){
        Usuario usuario = usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario",usuario);
        Usuario antiguo = usuarioRepositorio.getById(id);
        Usuario duplicado = usuarioRepositorio.findByEmail(competidor.getEmail());
        if(duplicado!= null && !duplicado.getEmail().equals(antiguo.getEmail())){
            model.addAttribute("error",true);
            model.addAttribute("competidor",antiguo);
            return "editarCompetidor";
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
        usuarioServicio.actualizarUsuario(antiguo);
        model.addAttribute("exito",true);
        model.addAttribute("competidor",antiguo);
        return "editarCompetidor";
    }

    @PostMapping("/registra")
    public String registra(HttpServletRequest request, Model model,Principal principal) {
        Usuario usuarioActual =  usuarioRepositorio.findByEmail(principal.getName());
        List<Evento> eventos = eventoRepositorio.findAll();
        String entrenador_email = usuarioActual.getEmail();
        model.addAttribute("usuario", usuarioActual);
        Date fecha = Date.valueOf(request.getParameter("fecha"));
        System.out.println(request.getParameter("eventos")+  "Evento");
        Usuario usuario = usuarioServicio.creaUsuarioCompetidor(request.getParameter("email"),
                request.getParameter("password"),
                request.getParameter("nombre"),
                request.getParameter("apellido_p"),
                request.getParameter("apellido_m"),
                request.getParameter("sexo"),
                fecha,
                Integer.parseInt(request.getParameter("peso")),
                Integer.parseInt(request.getParameter("altura")),
                entrenador_email,
                Integer.parseInt(request.getParameter("eventos"))
        );
        model.addAttribute("error", usuario == null);
        model.addAttribute("exito", usuario != null);
        model.addAttribute("eventos", eventos);
        return "registraCompetidor";
    }
    @RequestMapping("/buscar")
    public String busca(Model model, String error, Principal principal) {
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
        List<Usuario> usuarios = usuarioRepositorio.findCompetidoresRegistrados(usuario.getUsuario_Id());
        if (usuarios!=null)
        model.addAttribute("usuarios", usuarios);
        return "buscaCompetidores";
    }
    @GetMapping("eliminar/{id_Competidor}")
    private String eliminar (@PathVariable("id_Competidor") Integer id_Competidor){
        usuarioServicio.eliminarUsuario(id_Competidor);
        return "redirect:/entrenador/buscar";
    }
    @GetMapping("eliminarc/{id_Competidor}")
    private String eliminarc (@PathVariable("id_Competidor") Integer id_Competidor){
        usuarioServicio.eliminarUsuario(id_Competidor);
        return "redirect:/entrenador/calificaciones";
    }
    @RequestMapping("/calificaciones")
    public String calificaciones(Model modelo, String error, Principal principal) {
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        modelo.addAttribute("usuario", usuario);
        List<Usuario> competidores = usuarioRepositorio.findCompetidoresRegistrados(usuario.getUsuario_Id());
        if (competidores!=null)
            modelo.addAttribute("competidores", competidores);
        modelo.addAttribute("calificacionesRepositorio",calificacionRepositorio);
        return "calificacionCompetidores";
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
