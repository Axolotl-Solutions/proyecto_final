package com.unam.proyecto1.modelo;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "Usuario")
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

}
