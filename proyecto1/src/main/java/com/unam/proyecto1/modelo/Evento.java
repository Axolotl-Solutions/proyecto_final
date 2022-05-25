package com.unam.proyecto1.modelo;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Table(name = "Evento")
@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evento_Id")
    private Integer evento_Id;

    @Column(name = "nombre")
    private String nombreEvento;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "disciplina_Id")
    private Disciplina disciplina;

    @Column
    private String rama;

    @Column
    private String categoria;

    @Column
    private Date fecha;

    public Integer getEvento_Id() {
        return evento_Id;
    }

    public void setEvento_Id(Integer evento_Id) {
        this.evento_Id = evento_Id;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public String getRama() {
        return rama;
    }

    public void setRama(String rama) {
        this.rama = rama;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
