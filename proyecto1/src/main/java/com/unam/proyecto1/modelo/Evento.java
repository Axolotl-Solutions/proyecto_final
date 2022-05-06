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
    private String nombre_Disciplina;
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

    public String getNombre_Evento() {
        return nombre_Evento;
    }

    public void setNombre_Evento(String nombre_Evento) {
        this.nombre_Evento = nombre_Evento;
    }

    public String getNombre_Disciplina() {
        return nombre_Disciplina;
    }

    public void setNombre_Disciplina(String nombre_Disciplina) {
        this.nombre_Disciplina = nombre_Disciplina;
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
