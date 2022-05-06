package com.unam.proyecto1.controlador;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.unam.proyecto1.modelo.Usuario;
import com.unam.proyecto1.repositorio.UsuarioRepositorio;
import com.unam.proyecto1.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class PrincipalControlador {

    @RequestMapping("/")
    public String index(Model model, String error, Principal principal){
        if (error != null) {
            model.addAttribute("error", true);
        }

        if (principal != null) {
            System.out.println("Hola :)");
            System.out.println(model.toString());
            return "redirect:/usuarios/";
        }

        return "index";
    }

    /*@RequestMapping("/registrarCompetidor")
    public String registrarCompetidor() {
        return "registrarCompetidor";
    }*/

    @RequestMapping("/admins")
    public String manejoAdmin() { 
        return "admins"; 
    }

    /*@RequestMapping("/manejoAdmin")
    public String manejoAdmin() { 
        return "manejoAdmin"; 
    }*/

    @RequestMapping("/buscarComp")
    public  String buscarComp(){
        return "redirect:/registrarCompetidor/buscar/";
    }
    @RequestMapping("/registro")
    public String registro() { 
        return "registro";
         }

    @RequestMapping("/salir")
    public String salir(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
        session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        for (Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }
        return "redirect:/";
    }


}
