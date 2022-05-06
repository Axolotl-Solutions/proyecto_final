package com.unam.proyecto1.controlador;

import com.unam.proyecto1.modelo.Evento;
import com.unam.proyecto1.repositorio.EventoRepositorio;
import com.unam.proyecto1.servicio.EventoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/eventos")
public class EventoControlador {

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Autowired
    private EventoServicio eventoServicio;

    @GetMapping("/")
    public String findEvento(Model model) {
        List<Evento> eventos = eventoRepositorio.findAll();
        model.addAttribute("eventos", eventos);
        return "eventos";
    }


    @PostMapping("/crea")
    public String crea(HttpServletRequest request, Model model) {
        Date fecha = Date.valueOf(request.getParameter("fecha"));
        Evento evento = eventoServicio.creaEvento(request.getParameter("nombre_Evento"),
                request.getParameter("nombre_Disciplina"),
                request.getParameter("categoria"),
                request.getParameter("rama"),
                fecha);
        model.addAttribute("exito", evento != null);
        return "registro";
    }

}
