package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Administrador;

import java.util.List;

public interface IAdministradorServicio {
    List<Administrador> findAll();
}
