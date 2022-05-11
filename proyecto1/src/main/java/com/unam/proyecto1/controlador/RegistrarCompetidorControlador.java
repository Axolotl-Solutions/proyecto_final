package com.unam.proyecto1.controlador;

import com.unam.proyecto1.modelo.Competidor;
import com.unam.proyecto1.modelo.Usuario;
import com.unam.proyecto1.repositorio.CompetidorRepositorio;
import com.unam.proyecto1.servicio.CompetidorServicio;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/registrarCompetidor")
public class RegistrarCompetidorControlador {
    @Autowired
    private CompetidorRepositorio competidorRepositorio;

    @Autowired
    private CompetidorServicio competidorServicio;

    @PostMapping("/crea")
    public String crea(HttpServletRequest request, Model model) {
        Date fecha = Date.valueOf(request.getParameter("fecha"));
        String sexo = request.getParameter("sexo");
        sexo= sexo.equals("Masculino")?"Masculino":"Femenino";
        Competidor competidor = competidorServicio.creaCompetidor(request.getParameter("correo"),
                request.getParameter("password"),
                request.getParameter("nombre"),
                request.getParameter("apellido_p"),
                request.getParameter("apellido_m"),
                Integer.parseInt(request.getParameter("peso")),
                Integer.parseInt(request.getParameter("altura")),
                fecha,
                request.getParameter("sexo"));
        model.addAttribute("exito", competidor != null);
        return "usuarios";
    }
    @GetMapping("/buscar")
    public String findCompetidores(Model model) {
        List<Competidor> competidores = competidorRepositorio.findAll();
        model.addAttribute("competidores", competidores);
        return "buscar";
    }
    @GetMapping("/competidores")
    public String competidores(Model model) {
        List<Competidor> competidores = competidorRepositorio.findAll();
        model.addAttribute("competidores", competidores);
        return "competidores";
    }
    @GetMapping("/imprime")
    public String imprime(Model model) {
        model.addAttribute("competidores",null);
        return "buscar";
    }

    @GetMapping("/{id_Competidor}")
    private String eliminar (@PathVariable("id_Competidor") Integer id_Competidor){
        competidorServicio.eliminarCompetidor(id_Competidor);
        return "redirect:/registrarCompetidor/buscar/";
    }
    @GetMapping("/editar/{id_Competidor}")
    private String editar(@PathVariable("id_Competidor") Integer id_Competidor, Model modelo){
        modelo.addAttribute("competidor",competidorServicio.getCompetidorId(id_Competidor));
        return "editarCompetidor";
    }
    @PostMapping("/actualizar/{id}")
    public String actualizarEstudiante(@PathVariable Integer id,@ModelAttribute Competidor competidor,
                                       HttpServletRequest request){
        Competidor antiguo = competidorServicio.getCompetidorId(id);
        /* Falta ver el caso donde el correo ya esta registrado, pero
        * que no se el de el mismo :) */
        antiguo.setPeso(competidor.getPeso());
        antiguo.setAltura(competidor.getAltura());
        antiguo.setSexo(competidor.getSexo());
        Date date = Date.valueOf(request.getParameter("fecha"));
        antiguo.setFecha_nacimiento(date);
        antiguo.setNombre(competidor.getNombre());
        antiguo.setApellido_P(competidor.getApellido_P());
        antiguo.setApellido_M(competidor.getApellido_M());
        antiguo.setEmail(competidor.getEmail());
        competidorServicio.actualizarCompetidor(antiguo);
        return "redirect:/registrarCompetidor/buscar";
    }
}
