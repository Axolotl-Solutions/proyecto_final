package com.unam.proyecto1.controlador;

import com.unam.proyecto1.modelo.Evento;
import com.unam.proyecto1.modelo.Usuario;
import com.unam.proyecto1.repositorio.UsuarioRepositorio;
import com.unam.proyecto1.servicio.EmailService;
import com.unam.proyecto1.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
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

    @Autowired
    private EmailService emailService;

    
    @GetMapping("/registro")
    public String registra(){
        return "registro";
    }

    @GetMapping("/recuperar")
    public String recuperar(){
        return "recuperar";
    }

    @PostMapping("/confirmaRecupera")
    public String confirmaRecupera(Model model,HttpServletRequest request){
        String codigoIntroducido = request.getParameter("confirma");
        String correo = request.getParameter("correo");
        Usuario usuario = usuarioRepositorio.findByEmail(correo);
        String codigo = usuario.getCodigo();
        if(!codigoIntroducido.equals(codigo)){
            model.addAttribute("correo",correo);
            model.addAttribute("error");
            return "confirma";
        }else if(usuario!=null) {
            String contra = usuarioServicio.randomString(8);
            String asunto = "Axolotl Solutions inc - Sistema de Olimpiadas universitarias [Nueva contraseña] ";
            String mensaje = "Tu nueva contraseña es: \n\n"+ contra +"\n\nNo la olvides :)";
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            usuario.setPassword(passwordEncoder.encode(contra));
            emailService.sendSimpleMessage(usuario.getEmail(), asunto,mensaje);
            usuarioServicio.actualizarUsuario(usuario);
        }
        model.addAttribute("recupera",true);
        return "index";
    }

    @PostMapping("/recupera")
    public String recupera(HttpServletRequest request, Model model) throws MessagingException {
        Usuario usuario = usuarioRepositorio.findByEmail(request.getParameter("email"));
        String codigo = usuarioServicio.randomString(10);
        if(usuario!=null) {
            String asunto = "[Código de confirmación] Axolotl Solutions inc - Sistema de Olimpiadas universitarias";
            String mensaje = "El código de confirmación es: \n\n"+ codigo;
            usuario.setCodigo(codigo);
            usuarioRepositorio.save(usuario);
            emailService.sendSimpleMessage(usuario.getEmail(), asunto,mensaje);
        }else{
            model.addAttribute("error",true);
            return "recuperar";
        }
        model.addAttribute("correo",usuario.getEmail());
        model.addAttribute("exito",true);
        return "confirma";
    }
    @PostMapping("/crea")
    public String crea(@RequestParam("imagen") MultipartFile imagen, HttpServletRequest request, Model model) throws MessagingException {
        String contra = usuarioServicio.randomString(10);
        String nombre = request.getParameter("nombre")+" "+
                request.getParameter("apellido_p")+" "+
                request.getParameter("apellido_m");
        String asunto = "Axolotl Solutions inc - Sistema de Olimpiadas universitarias [contraseña]";
        Usuario usuario = usuarioServicio.creaUsuarioEntrenador(request.getParameter("email"),
                contra,
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
        if (usuario!=null) {
            emailService.sendCorreoTemplate(request.getParameter("email"), asunto,
                    usuario,contra);
        }
        model.addAttribute("exito", usuario != null);
        return "registro";
    }

}
