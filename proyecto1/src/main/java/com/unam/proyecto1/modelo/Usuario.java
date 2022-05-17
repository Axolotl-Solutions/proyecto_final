package com.unam.proyecto1.modelo;

import javax.persistence.*;

import lombok.Data;

import java.util.*;

@Data
@Table(name = "Usuario")
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_Id")
    private Integer usuario_Id;

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

    @Column
    private Date fecha_nacimiento;

    @Column
    private String sexo;

    @Column
    private Integer peso;

    @Column
    private Integer altura;

    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "Usuarios_Roles",
            joinColumns = @JoinColumn(name = "usuario_Id"),
            inverseJoinColumns = @JoinColumn(name = "rol_Id")
    )
    private Set<Rol> roles = new HashSet<>();

    public Integer getUsuario_Id() {
        return usuario_Id;
    }

    public void setUsuario_Id(Integer id_Administrador) {
        this.usuario_Id = id_Administrador;
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

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean hasRole(String nombreRol){
        Iterator<Rol> iterator = this.roles.iterator();
        while(iterator.hasNext()){
            Rol rol = iterator.next();
            if(rol.getNombre().equals(nombreRol)){
                return true;
            }
        }
        return false;
    }
}
