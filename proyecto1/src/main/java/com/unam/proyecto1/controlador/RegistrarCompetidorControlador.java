package com.unam.proyecto1.controlador;

import com.unam.proyecto1.modelo.Competidor;
import com.unam.proyecto1.repositorio.CompetidorRepositorio;
import com.unam.proyecto1.servicio.CompetidorServicio;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/registrarCompetidor")
public class RegistrarCompetidorControlador {
    @Autowired
    private CompetidorRepositorio usuarioRepositorio;

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

        System.out.printf(competidor.getEmail()+"**********************************");
        return "usuarios";
    }
}
