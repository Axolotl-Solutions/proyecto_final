package com.unam.proyecto1.modelo;

import com.unam.proyecto1.utils.Rol;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "Competidor")
@Entity
public class Competidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Competidor")
    private Integer id_Competidor;

    @Column
    private String nombre;

    @Column
    private String apellido_P;

    @Column
    private String apellido_M;

    @Column
    private Integer peso;

    @Column
    private Integer altura;

    @Column
    private Date fecha_nacimiento;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String sexo;

    @Column
    @Enumerated(EnumType.STRING)
    private Rol rol;

    /* Setters: */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido_P(String apellido_P) {
        this.apellido_P = apellido_P;
    }

    public void setApellido_M(String apellido_M) {
        this.apellido_M = apellido_M;
    }

    public void setId_Competidor(Integer id_Competidor) {
        this.id_Competidor = id_Competidor;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    /* Getters */

    public String getNombre() {
        return nombre;
    }

    public String getApellido_P() {
        return apellido_P;
    }

    public String getApellido_M() {
        return apellido_M;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public Integer getId_Competidor() {
        return id_Competidor;
    }

    public Integer getAltura() {
        return altura;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public Integer getPeso() {
        return peso;
    }

    public Rol getRol() {
        return rol;
    }
    
    public String getSexo() {
        return sexo;
    }
}
