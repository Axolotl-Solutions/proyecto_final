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

    public Integer getDisciplina_Id() {
        return disciplina_Id;
    }

    public void setDisciplina_Id(Integer disciplina_Id) {
        this.disciplina_Id = disciplina_Id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
