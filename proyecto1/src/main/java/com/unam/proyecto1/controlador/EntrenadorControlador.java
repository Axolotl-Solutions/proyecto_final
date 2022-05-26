package com.unam.proyecto1.controlador;

import com.unam.proyecto1.modelo.Disciplina;
import com.unam.proyecto1.modelo.Usuario;
import com.unam.proyecto1.repositorio.DisciplinaRepositorio;
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

@Controller
@RequestMapping("/entrenador")
public class EntrenadorControlador {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private DisciplinaRepositorio disciplinaRepositorio;

    @GetMapping("/")
    public String perfil(Model model, Principal principal) {
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
        return "inicioEntrenador";
    }
    @GetMapping("/registrar")
    public String registrar(HttpServletRequest request,Model model, Principal principal) {
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
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
        model.addAttribute("usuario", usuarioActual);
        Date fecha = Date.valueOf(request.getParameter("fecha"));
        Usuario usuario = usuarioServicio.creaUsuarioCompetidor(request.getParameter("email"),
                request.getParameter("password"),
                request.getParameter("nombre"),
                request.getParameter("apellido_p"),
                request.getParameter("apellido_m"),
                request.getParameter("sexo"),
                fecha,
                Integer.parseInt(request.getParameter("peso")),
                Integer.parseInt(request.getParameter("altura"))
        );
        model.addAttribute("error", usuario == null);
        model.addAttribute("exito", usuario != null);
        return "registraCompetidor";
    }
    @RequestMapping("/buscar")
    public String busca(Model model, String error, Principal principal) {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        for (int i=0; i< usuarios.size();i++){
            if(usuarios.get(i) != null){
                if(!usuarios.get(i).getRol().equals("COMPETIDOR")){
                    System.out.println(usuarios.get(i).getRol());
                    usuarios.remove(i);
                }
            }
        }
        System.out.println(usuarios.size()+" SIZE");
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuarios", usuarios);
        return "buscaCompetidores";
    }
    @GetMapping("eliminar/{id_Competidor}")
    private String eliminar (@PathVariable("id_Competidor") Integer id_Competidor){
        //error, elimina tambien el Rol
        usuarioServicio.eliminarUsuario(id_Competidor);
        return "redirect:/entrenador/buscar";
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
