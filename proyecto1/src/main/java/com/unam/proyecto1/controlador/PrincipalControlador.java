package com.unam.proyecto1.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
            return "redirect:/usuarios/";
        }
        return "index";
    }


}
