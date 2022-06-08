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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
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
            return "editaJuez";
        }
        antiguo.setEmail(competidor.getEmail());
        antiguo.setNombre(competidor.getNombre());
        antiguo.setApellido_P(competidor.getApellido_P());
        antiguo.setApellido_M(competidor.getApellido_M());
        antiguo.setDisciplinaJuez(disciplinaRepositorio.findByNombre(request.getParameter("nombreDisciplina")));
        usuarioServicio.actualizarUsuario(antiguo);
        List<Disciplina> disciplinas = disciplinaRepositorio.findAll();
        model.addAttribute("disciplinas",disciplinas);
        model.addAttribute("exito",true);
        model.addAttribute("competidor",antiguo);
        return "editaJuez";
    }
    @GetMapping("editarJuez/{email}")
    private String editar(@PathVariable("email") String email, Model modelo,
                          Principal principal){
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        Usuario competidor =  usuarioRepositorio.findByEmail(email);
        List<Disciplina> disciplinas = disciplinaRepositorio.findAll();
        modelo.addAttribute("disciplinas",disciplinas);
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("competidor",competidor);
        return "editaJuez";
    }
    @GetMapping("eliminarJuez/{id}")
    private String eliminarJuez(@PathVariable("id") Integer id,Principal principal,Model model){
        usuarioServicio.eliminarUsuario(id);
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        List<Usuario> nuevos = new ArrayList<>();
        for (Usuario u:usuarios){
            if(u.hasRole("ROLE_JUEZ")){
                nuevos.add(u);
            }
        }
        if (usuarios!=null)
            model.addAttribute("usuarios", nuevos);
        model.addAttribute("exito",true);
        return "buscaJuez";
    }
    @RequestMapping("/buscaJuez")
    public String buscaJuez(Model model, String error, Principal principal) {
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        List<Usuario> nuevos = new ArrayList<>();
        for (Usuario u:usuarios){
            if(u.hasRole("ROLE_JUEZ")){
                nuevos.add(u);
            }
        }
        if (usuarios!=null)
            model.addAttribute("usuarios", nuevos);
        return "buscaJuez";
    }
    @RequestMapping("/busca")
    public String busca(Model model, String error, Principal principal) {
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        List<Usuario> nuevos = new ArrayList<>();
        for (Usuario u:usuarios){
            if(u.hasRole("ROLE_COMPETIDOR") || u.hasRole("ROLE_ENTRENADOR")){
                nuevos.add(u);
            }
        }
        if (usuarios!=null)
            model.addAttribute("usuarios", nuevos);
        return "buscaCompetidorEntrenador";
    }
    @PostMapping("/actualizaDisciplina/{id}")
    private String actualizaDisciplina(@PathVariable("id") Integer id,Model modelo,
                                    Principal principal,HttpServletRequest request){
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        Disciplina antigua = disciplinaRepositorio.getById(id);
        Disciplina nueva = disciplinaRepositorio.findByNombre(request.getParameter("nombreDisciplina"));
        if (nueva == null || antigua.getNombre().equals(nueva.getNombre())){
            antigua.setNombre(request.getParameter("nombreDisciplina"));
            disciplinaServicio.actualizarDisciplina(antigua);
        }
        modelo.addAttribute("error", nueva != null);
        modelo.addAttribute("exito", nueva == null);
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("disciplina", antigua);
        return "editarDisciplina";
    }
    @GetMapping("editaDisciplina/{id}")
    private String editarDisciplina(@PathVariable("id") Integer id, Model modelo,
                          Principal principal){
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        Disciplina disciplina =  disciplinaRepositorio.getById(id);

        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("disciplina",disciplina);
        System.out.println("LOL XD");
        System.out.println(disciplina.getNombre());
        return "editarDisciplina";
    }
    @GetMapping("/eliminarDisciplina/{id}")
    public String editaEvento(@PathVariable Integer id, Principal principal, Model model){
        Usuario usuario = usuarioRepositorio.findByEmail(principal.getName());
        disciplinaServicio.eliminaDisciplina(id);
        List<Disciplina> disciplinas = disciplinaRepositorio.findAll();
        model.addAttribute("disciplinas",disciplinas);
        model.addAttribute("usuario",usuario);
        model.addAttribute("exito",true);
        System.out.println("IDDisciplina: "+id);
        return "buscarDisciplina";
    }
    @GetMapping("/buscarDisciplina")
    public String buscar(Principal principal,Model model){
        Usuario usuarioActual =  usuarioRepositorio.findByEmail(principal.getName());
        List<Disciplina> disciplinas = disciplinaRepositorio.findAll();
        model.addAttribute("usuario", usuarioActual);
        model.addAttribute("disciplinas",disciplinas);
        return "buscarDisciplina";
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
    public String creaJuez(@RequestParam("imagen") MultipartFile imagen, HttpServletRequest request, Model model, Principal principal){
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
        if (!imagen.isEmpty()){
            Path directorioImagenes = Paths.get("src//main//resources//static//img");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta+"//"+imagen.getOriginalFilename());
                Files.write(rutaCompleta,bytesImg);
                if (nuevoJuez!=null) {
                    nuevoJuez.setImagen(imagen.getOriginalFilename());
                    usuarioServicio.actualizarUsuario(nuevoJuez);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Usuario usuarioActual =  usuarioRepositorio.findByEmail(principal.getName());
        List<Disciplina> disciplinas = disciplinaRepositorio.findAll();
        model.addAttribute("disciplinas",disciplinas);
        model.addAttribute("usuario", usuarioActual);
        return "admins";
    }

}
