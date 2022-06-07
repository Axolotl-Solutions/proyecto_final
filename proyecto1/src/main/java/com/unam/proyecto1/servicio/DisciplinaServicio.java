package com.unam.proyecto1.servicio;

import com.unam.proyecto1.modelo.Disciplina;

public interface DisciplinaServicio {
    Disciplina creaDisciplina(String nombre);

    void eliminaDisciplina(Integer id);

    Disciplina actualizarDisciplina(Disciplina disciplina);
}
