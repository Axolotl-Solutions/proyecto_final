package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Usuario;

import javax.mail.MessagingException;

public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);

    void sendCorreoTemplate(String to, String subject, Usuario usuario,String c) throws MessagingException;
}
