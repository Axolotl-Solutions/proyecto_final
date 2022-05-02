package com.unam.proyecto1.modelo;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "Administrador")
@Entity
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Administrador")
    private Integer id_Administrador;
    @Column
    private String nombre;
    @Column
    private String apellido_P;
    @Column
    private String apellido_M;
    @Column
    private String email;
    @Column
    private String password;

    public Integer getId_Administrador() {
        return id_Administrador;
    }

    public void setId_Administrador(Integer id_Administrador) {
        this.id_Administrador = id_Administrador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_P() {
        return apellido_P;
    }

    public void setApellido_P(String apellido_P) {
        this.apellido_P = apellido_P;
    }

    public String getApellido_M() {
        return apellido_M;
    }

    public void setApellido_M(String apellido_M) {
        this.apellido_M = apellido_M;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
