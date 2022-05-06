package com.unam.proyecto1.controlador;

import com.unam.proyecto1.modelo.Juez;
import com.unam.proyecto1.repositorio.JuezRepositorio;
import com.unam.proyecto1.servicio.JuezServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/jueces")
public class JuezControlador {

    @Autowired
    private JuezRepositorio juezRepositorio;

    @Autowired
    private JuezServicio juezServicio;

    /* Método para buscar a todos los jueces de la lista del sistema de olimpiadas. */
    @GetMapping("/")
    public String buscarJuez(Model model) {
        List<Juez> jueces = juezRepositorio.findAll();
        model.addAttribute("jueces", jueces);
        return "jueces";
    }

    /* Método para registrar a un juez nuevo. */
    @PostMapping("/crea")
    public String crea(HttpServletRequest request, Model model) {
        Juez juez = juezServicio.creaJuez(request.getParameter("nombre_Juez"),
                request.getParameter("nombre_Disciplina"),
                request.getParameter("categoria"),
                request.getParameter("rama"));
        model.addAttribute("exito", juez != null);
        return "registro";
    }
    
}
