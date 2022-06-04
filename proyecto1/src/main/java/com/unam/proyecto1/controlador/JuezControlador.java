package com.unam.proyecto1.controlador;

import com.unam.proyecto1.modelo.Disciplina;
import com.unam.proyecto1.modelo.Evento;
import com.unam.proyecto1.modelo.Usuario;
import com.unam.proyecto1.repositorio.EventoRepositorio;
import com.unam.proyecto1.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/jueces")
public class JuezControlador {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    EventoRepositorio eventoRepositorio;

    @GetMapping("/")
    public String perfil(Model model, Principal principal) {
        Usuario usuario = usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
        Disciplina disciplinaJuez = usuario.getDisciplinaJuez();
        List<Evento> eventos = eventoRepositorio.findEventosByDisciplina(disciplinaJuez.getDisciplina_Id());
        model.addAttribute("eventos", eventos);
        model.addAttribute("usuarioRepositorio", usuarioRepositorio);
        return "inicioJueces";
    }

}
