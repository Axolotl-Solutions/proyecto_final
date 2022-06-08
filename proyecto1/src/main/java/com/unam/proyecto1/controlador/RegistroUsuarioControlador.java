package com.unam.proyecto1.controlador;

import com.unam.proyecto1.modelo.Usuario;
import com.unam.proyecto1.repositorio.UsuarioRepositorio;
import com.unam.proyecto1.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    
    @GetMapping("/registro")
    public String registra(){
        return "registro";
    }


    @PostMapping("/crea")
    public String crea(@RequestParam("imagen") MultipartFile imagen, HttpServletRequest request, Model model) {
        Usuario usuario = usuarioServicio.creaUsuarioEntrenador(request.getParameter("email"),
                request.getParameter("password"),
                request.getParameter("nombre"),
                request.getParameter("apellido_p"),
                request.getParameter("apellido_m"));
        if (!imagen.isEmpty()){
            Path directorioImagenes = Paths.get("src//main//resources//static//img");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta+"//"+imagen.getOriginalFilename());
                Files.write(rutaCompleta,bytesImg);
                if (usuario!=null) {
                    usuario.setImagen(imagen.getOriginalFilename());
                    usuarioServicio.actualizarUsuario(usuario);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("exito", usuario != null);
        return "registro";
    }

}
