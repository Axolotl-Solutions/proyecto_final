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
}
