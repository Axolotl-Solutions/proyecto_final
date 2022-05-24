package com.unam.proyecto1.controlador;

import com.unam.proyecto1.modelo.Disciplina;
import com.unam.proyecto1.modelo.Usuario;
import com.unam.proyecto1.repositorio.DisciplinaRepositorio;
import com.unam.proyecto1.repositorio.UsuarioRepositorio;
import com.unam.proyecto1.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        //usuarioServicio.eliminarUsuario(id_Competidor);
        return "redirect:/entrenador/buscar";
    }
    @GetMapping("editar/{id_Competidor}")
    private String editar(@PathVariable("id_Competidor") String id_Competidor, Model modelo,Principal principal
    ){
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        Usuario competidor =  usuarioRepositorio.findByEmail(id_Competidor);
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("competidor",competidor);
        return "editarCompetidor";
    }

}
