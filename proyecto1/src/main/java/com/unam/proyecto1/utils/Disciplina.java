package com.unam.proyecto1.utils;

public enum Disciplina {
    FUTBOL ("Fútbol"),
    TENIS ("Tenis"),
    BASQUETBOL ("Basquetbol"),
    VOLEIBOL ("Voleibol"),
    ARQUERIA ("Arquería"),
    AJEDREZ ("Ajedrez"),
    ATLETISMO ("Atletismo"),
    GIMNASIA ("Gimnasia"),
    NATACION ("Natación"),
    TAEKWONDO ("Taekwondo");


    private final String nombre;

    Disciplina(String nombre) {
        this.nombre = nombre;
    }

    private String getNombre(){
        return this.nombre;
    }
}
