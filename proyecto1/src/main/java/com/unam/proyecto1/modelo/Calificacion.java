package com.unam.proyecto1.modelo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "Calificacion")
@Entity
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calificacion_Id")
    private Integer calificacionId;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "evento_Id")
    private Evento evento;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "juez_Id")
    private Usuario juez;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "competidor_id")
    private Usuario competidor;

    @Column
    private Double puntaje;

    @Column
    private String comentario;

    public Integer getCalificacionId() {
        return calificacionId;
    }

    public void setCalificacionId(Integer calificacionId) {
        this.calificacionId = calificacionId;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Usuario getJuez() {
        return juez;
    }

    public void setJuez(Usuario juez) {
        this.juez = juez;
    }

    public Usuario getCompetidor() {
        return competidor;
    }

    public void setCompetidor(Usuario competidor) {
        this.competidor = competidor;
    }

    public Double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Double puntaje) {
        this.puntaje = puntaje;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
