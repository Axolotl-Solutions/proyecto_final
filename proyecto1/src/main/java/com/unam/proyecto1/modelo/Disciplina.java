package com.unam.proyecto1.modelo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Disciplina")
public class Disciplina {
    @Id
    @Column(name = "disciplina_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer disciplina_Id;

    @Column
    private String nombre;
}
