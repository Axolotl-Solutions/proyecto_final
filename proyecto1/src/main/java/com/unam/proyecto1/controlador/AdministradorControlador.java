package com.unam.proyecto1.controlador;

import com.unam.proyecto1.modelo.Administrador;
import com.unam.proyecto1.repositorio.AdministradorRepositorio;
import com.unam.proyecto1.servicio.IAdministradorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller

public class AdministradorControlador {

    @Autowired
    private IAdministradorServicio adminServicio;

    @GetMapping("/admins")
    public String findAdmins(Model model) {
        List<Administrador> admins = (List<Administrador>) adminServicio.findAll();
        model.addAttribute("admins", admins);
        return "admins";
    }

}
