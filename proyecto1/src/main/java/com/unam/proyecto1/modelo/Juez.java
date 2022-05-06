package com.unam.proyecto1.modelo;

//import com.unam.proyecto1.utils.Rol;
import lombok.Data;
import javax.persistence.*;

/** Clase de los jueces para el sistema de olimpiadas. 
Los jueces tiene un ID, un nombre, la disciplina a la que
pertenecen, la rama de dicha disciplina y su categoría. */
@Data
@Table(name = "Juez")
@Entity
public class Juez {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* Columna con el ID de cada juez. */
    @Column(name = "id_Juez")
    private Integer id_Juez;
    /* Columna con el nombre de cada juez */
    @Column(name = "nombre_Juez")
    private String nombre_Juez;
    /* Columna con el nombre de la disciplina a la que pertenece el juez */
    @Column
    private String nombre_Disciplina;
    /* Columna con la rama de la disciplina. */
    @Column
    private String rama;
    /* Columna con la categoría de la disciplina. */
    @Column
    private String categoria;

    public Integer getId_Juez() {
        return id_Juez;
    }

    public void setId_Juez(Integer id_Juez) {
        this.id_Juez = id_Juez;
    }

    public String getNombre_Juez() {
        return nombre_Juez;
    }

    public void setNombreJuez(String nombre_Juez) {
        this.nombre_Juez = nombre_Juez;
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

}
