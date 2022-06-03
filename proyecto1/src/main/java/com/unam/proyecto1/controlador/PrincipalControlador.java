package com.unam.proyecto1.controlador;

import com.unam.proyecto1.modelo.Disciplina;
import com.unam.proyecto1.repositorio.DisciplinaRepositorio;
import com.unam.proyecto1.servicio.DisciplinaServicio;
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
import java.util.List;

@Controller
public class PrincipalControlador {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private DisciplinaRepositorio disciplinaRepositorio;




    @RequestMapping("/inicio")
    public String inicio(HttpServletRequest request, Model model) {
        String nombres=" Lorem ipsum dolor sit amet. Eum autem facilis et facere placeat ut quia praesentium ut provident soluta ut magnam obcaecati At ipsum perspiciatis. Et dolorem assumenda 33 libero blanditiis qui sunt adipisci a consectetur molestiae 33 deleniti dolore non omnis consequatur. Vel officiis quia id omnis impedit sed quibusdam voluptates quo voluptatibus similique ut praesentium animi.\n" +
                "\n" +
                "Id sint omnis qui quod ducimus est nulla excepturi eos excepturi dolores vel officia rerum ab voluptatem ipsam. Non quisquam fuga vel odit laudantium sit dignissimos aliquid est quod doloremque est doloremque voluptatem 33 tenetur iste et suscipit enim. Qui laboriosam provident qui explicabo dolor eum nostrum voluptatum nam delectus voluptas?\n" +
                "\n" +
                "Vel rerum dolores sed doloribus cumque est rerum sint est voluptas suscipit. Cum vero veniam ea dignissimos deserunt sed repellendus aliquam.Lorem ipsum dolor sit amet. Eum autem facilis et facere placeat ut quia praesentium ut provident soluta ut magnam obcaecati At ipsum perspiciatis. Et dolorem assumenda 33 libero blanditiis qui sunt adipisci a consectetur molestiae 33 deleniti dolore non omnis consequatur. Vel officiis quia id omnis impedit sed quibusdam voluptates quo voluptatibus similique ut praesentium animi.\n" +
                "\n" +
                "Id sint omnis qui quod ducimus est nulla excepturi eos excepturi dolores vel officia rerum ab voluptatem ipsam. Non quisquam fuga vel odit laudantium sit dignissimos aliquid est quod doloremque est doloremque voluptatem 33 tenetur iste et suscipit enim. Qui laboriosam provident qui explicabo dolor eum nostrum voluptatum nam delectus voluptas?\n" +
                "\n" +
                "Vel rerum dolores sed doloribus cumque est rerum sint est voluptas suscipit. Cum vero veniam ea dignissimos deserunt sed repellendus aliquam.";
        model.addAttribute("nombre", nombres);
        return "inicio";
    }
    @RequestMapping("/")
    public String index(Model model, String error, Principal principal){
        if (error != null) {
            System.out.println("Hola error");
            model.addAttribute("error", true);
        }

        if (principal != null) {
            return "redirect:/usuarios/";
        }

        return "index";
    }

    @RequestMapping("/admins")
    public String manejoAdmin(Model model,Principal principal) {
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
        List<Disciplina> disciplinas = disciplinaRepositorio.findAll();
        model.addAttribute("disciplinas", disciplinas);
        return "admins";
    }


    @RequestMapping("/inicioAdmin")
    public String inicioAdmin(Model model, String error, Principal principal) {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        List<Disciplina> disciplinas =disciplinaRepositorio.findAll();
        Usuario usuario =  usuarioRepositorio.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("disciplinas",disciplinas);
        return "inicioAdmin";
    }

    /*@RequestMapping("/manejoAdmin")
    public String manejoAdmin() { 
        return "manejoAdmin"; 
    }*/

    @RequestMapping("/buscarComp")
    public  String buscarComp(){
        return "redirect:/registrarCompetidor/buscar/";
    }

    @RequestMapping("/inicioCompetidor")
    public String competidor() {
        System.out.println("competidor");

        return "inicioCompetidor/";
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
