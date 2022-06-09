package com.unam.proyecto1.controlador;

import com.unam.proyecto1.modelo.Calificacion;
import com.unam.proyecto1.modelo.Evento;
import com.unam.proyecto1.modelo.Usuario;
import com.unam.proyecto1.repositorio.CalificacionRepositorio;
import com.unam.proyecto1.repositorio.DisciplinaRepositorio;
import com.unam.proyecto1.repositorio.EventoRepositorio;
import com.unam.proyecto1.repositorio.UsuarioRepositorio;
import com.unam.proyecto1.servicio.EmailService;
import com.unam.proyecto1.servicio.UsuarioServicio;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
    @Autowired
    EmailService emailService;

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
    @GetMapping("agregarEvento/{idC}")
    private String agregarEvento (@PathVariable("idC") Integer idC,Principal principal,HttpServletRequest request, Model model){
        Usuario competidor = usuarioRepositorio.getById(idC);
        Usuario usuario = usuarioRepositorio.findByEmail(principal.getName());
        Evento evento = eventoRepositorio.getById(Integer.valueOf(request.getParameter("eventos")));
        List<Evento> eventosAll = eventoRepositorio.findAll();
        Set<Evento> eventos = competidor.getEventos();
        String sexo = evento.getRama().equals("Femenil")? "Femenino":"Masculino";
        sexo = evento.getRama().equals("Mixto")?competidor.getSexo():sexo;
        if(!competidor.hasEvento(evento) && sexo.equals(competidor.getSexo())) {
            competidor.getEventos().add(evento);
        }else{
            model.addAttribute("error",true);
        }
        usuarioServicio.actualizarUsuario(competidor);
        model.addAttribute("usuario",usuario);
        model.addAttribute("competidor",competidor);
        model.addAttribute("eventos",eventos);
        model.addAttribute("eventosAll",eventosAll);
        model.addAttribute("calificacionRepositorio",calificacionRepositorio);
        return "eventosCompetidor";
    }
    @GetMapping("eliminarEvento/{idC}/{idE}")
    private String eliminarEvento (@PathVariable("idC") Integer idC, @PathVariable("idE") Integer idE){
        Usuario competidor = usuarioRepositorio.getById(idC);
        Evento evento = eventoRepositorio.getById(idE);
        System.out.println(evento+ " A eliminar");
        System.out.println(competidor.getNombre()+" NOMBRE");
        System.out.println(competidor.getEventos());
        competidor.getEventos().remove(eventoRepositorio.getById(idE));
        System.out.println(competidor.getEventos());
        usuarioServicio.actualizarUsuario(competidor);
        return "redirect:/entrenador/editarEventos/"+competidor.getEmail();
    }
    @GetMapping("/editarEventos/{email}")
    public String editaEvento(@PathVariable String email,@ModelAttribute Usuario competidor,
                        HttpServletRequest request,Principal principal,
                        Model model){
        List<Evento> eventosAll = eventoRepositorio.findAll();
        Usuario usuario = usuarioRepositorio.findByEmail(principal.getName());
        Usuario comp = usuarioRepositorio.findByEmail(email);
        Set<Evento> eventos = competidor.getEventos();
        System.out.println(request.getParameter("email"));
        model.addAttribute("usuario",usuario);
        model.addAttribute("competidor",comp);
        model.addAttribute("eventos",eventos);
        model.addAttribute("eventosAll",eventosAll);
        model.addAttribute("calificacionRepositorio",calificacionRepositorio);
        System.out.println("Entre aquí");
        return "eventosCompetidor";
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
    public String registra(@RequestParam("imagen") MultipartFile imagen, HttpServletRequest request, Model model, Principal principal) throws MessagingException {
        Usuario usuarioActual =  usuarioRepositorio.findByEmail(principal.getName());
        List<Evento> eventos = eventoRepositorio.findAll();
        model.addAttribute("eventos", eventos);
        String entrenador_email = usuarioActual.getEmail();
        model.addAttribute("usuario", usuarioActual);
        Date fecha = Date.valueOf(request.getParameter("fecha"));
        String sexo = eventoRepositorio.getById(Integer.valueOf(request.getParameter("eventos"))).getRama().equals("Femenil")?"Femenino":"Masculino";
        sexo = eventoRepositorio.getById(Integer.valueOf(request.getParameter("eventos"))).getRama().equals("Mixto")?request.getParameter("sexo"):sexo;
        String contra = usuarioServicio.randomString(8);
        Evento e = eventoRepositorio.getById(Integer.parseInt(request.getParameter("eventos")));
        String evento = e.getNombreEvento();
        String fechaEvento = String.valueOf(e.getFecha());
        String rama = e.getRama();
        String disciplina = e.getDisciplina().getNombre();
        String nombre = request.getParameter("nombre")+" "+
        request.getParameter("apellido_p")+" "+
                request.getParameter("apellido_m");
        String asunto = "Axolotl Solutions inc - Sistema de Olimpiadas universitarias [contraseña]";
        Usuario usuario = null;
        if (sexo.equals(request.getParameter("sexo"))) {
             usuario = usuarioServicio.creaUsuarioCompetidor(request.getParameter("email"),
                    contra,
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
        }else{
            model.addAttribute("errorSexo",true);
            return "registraCompetidor";
        }

        if (!imagen.isEmpty()){
            Path directorioImagenes = Paths.get("src//main//resources//static//img");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta+"//"+imagen.getOriginalFilename());
                Files.write(rutaCompleta,bytesImg);
                if (usuario!=null) {
                    usuario.setImagen(imagen.getOriginalFilename());
                    usuarioServicio.actualizarUsuario(usuario);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (usuario!=null) {
            emailService.sendCorreoTemplate(request.getParameter("email"), asunto,usuario,contra);
        }
        model.addAttribute("error", usuario == null);
        model.addAttribute("exito", usuario != null);
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
                    usr1.getNombre() +" "+ usr1.getApellido_P()+" "+usr1.getApellido_M());
            modelo.addAttribute("tercero",
                    usr2.getNombre() +" "+ usr2.getApellido_P()+" "+usr2.getApellido_M());
        }else if(idCompetidores.size()==2){
            Usuario usr = usuarioRepositorio.getById(idCompetidores.get(0));
            Usuario usr1 = usuarioRepositorio.getById(idCompetidores.get(1));
            modelo.addAttribute("primero",
                    usr.getNombre() +" "+ usr.getApellido_P()+" "+usr.getApellido_M());
            modelo.addAttribute("segundo",
                    usr1.getNombre() +" "+ usr1.getApellido_P()+" "+usr1.getApellido_M());
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
        modelo.addAttribute("competidores",competidores);
        modelo.addAttribute("calificacionRepositorio",calificacionRepositorio);
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("evento",e);
        modelo.addAttribute("eventos",eventoRepositorio.findAll());
        return "Tabla";
    }
    @RequestMapping("/tabla")
    public String tabla(Model modelo, String error, Principal principal) {
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        List<Evento> eventos = eventoRepositorio.findAll();
        if (eventos.size()>0){
            return "redirect:/entrenador/tabla/1";
        }
        List<Calificacion> cal = calificacionRepositorio.findAll();
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("eventos",eventos);
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
