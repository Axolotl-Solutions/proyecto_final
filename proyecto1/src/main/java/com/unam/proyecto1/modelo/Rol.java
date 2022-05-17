package com.unam.proyecto1.modelo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Rol")
public class Rol {
    @Id
    @Column(name = "rol_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rol_id;

    @Column
    private String nombre;

    public Integer getRol_id() {
        return rol_id;
    }

    public void setRol_id(Integer rol_id) {
        this.rol_id = rol_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
