package com.unam.proyecto1.servicio;

public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);
}
