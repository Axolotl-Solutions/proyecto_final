package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private TemplateEngine templateEngine;
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
    @Override
    public void sendCorreoTemplate(String to, String subject, Usuario usuario,
                                   String contra) throws MessagingException {
        Context context = new Context();
        context.setVariable("usuario",usuario);
        context.setVariable("contra",contra);
        String htmlContenido = templateEngine.process("correoTemplate",context);
        final MimeMessage mimeMessage = this.emailSender.createMimeMessage();
        final MimeMessageHelper mensaje = new MimeMessageHelper(mimeMessage, true);
        mensaje.setTo(to);
        mensaje.setSubject(subject);
        mensaje.setText(htmlContenido,true);
        File archivo = new File("src//main//resources//static//img//logomail.png");
        FileSystemResource file = new FileSystemResource(archivo);
        mensaje.addInline(archivo.getName(), file);
        this.emailSender.send(mimeMessage);
    }
}
