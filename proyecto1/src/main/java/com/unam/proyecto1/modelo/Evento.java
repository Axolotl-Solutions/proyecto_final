package com.unam.proyecto1.modelo;

import com.unam.proyecto1.utils.Disciplina;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Table(name = "Evento")
@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Evento")
    private Integer id_Evento;
    @Column
    private String nombre_Evento;
    @Column
    @Enumerated(EnumType.STRING)
    private Disciplina nombre_Disciplina;
    @Column
    private String rama;
    @Column
    private String categoria;
    @Column
    private Date fecha;
}
