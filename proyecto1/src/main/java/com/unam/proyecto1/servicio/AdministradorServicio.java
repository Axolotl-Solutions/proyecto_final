package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Administrador;
import com.unam.proyecto1.repositorio.AdministradorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorServicio implements IAdministradorServicio {

    @Autowired
    private AdministradorRepositorio adminRepo;


    @Override
    public List<Administrador> findAll() {
        List<Administrador> admins = (List<Administrador>) adminRepo.findAll();
        return admins;
    }
}
