package com.edutech.cl.edutech.dto.response;


import com.edutech.cl.edutech.model.Curso;
import lombok.Data;

@Data
public class CursoDTO {

    private Long id;
    private String nombreCurso;
    private String descripcionCurso;
    private String valorCurso;
    private String metodoPago;

    public CursoDTO(Curso curso) {
        this.id = curso.getId();
        this.nombreCurso = curso.getNombreCurso();
        this.descripcionCurso = curso.getDescripcionCurso();
        this.valorCurso = curso.getValorCurso();
        this.metodoPago = curso.getMetodoPago();

    }

}
