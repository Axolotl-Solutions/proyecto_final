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
    @Column(name = "id_Evento")
    private Integer id_Evento;

    @Column(name = "nombre_Evento")
    private String nombreEvento;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Disciplina disciplina;

    @Column
    private String rama;

    @Column
    private String categoria;

    @Column
    private Date fecha;

    public Integer getId_Evento() {
        return id_Evento;
    }

    public void setId_Evento(Integer id_Evento) {
        this.id_Evento = id_Evento;
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
