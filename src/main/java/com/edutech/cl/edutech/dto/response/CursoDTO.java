package com.edutech.cl.edutech.dto.response;


import com.edutech.cl.edutech.model.Curso;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CursoDTO {

    private Long id;
    private String nombreCurso;
    private String descripcionCurso;
    private String valorCurso;
    private String metodoPago;
    List<MetodoPagoDTO> metodoPagoList = new ArrayList<>();

    public CursoDTO(Curso curso) {
        this.id = curso.getId();
        this.nombreCurso = curso.getNombreCurso();
        this.descripcionCurso = curso.getDescripcionCurso();
        this.valorCurso = curso.getValorCurso();
        this.metodoPago = curso.getMetodoPago();

    }

    public CursoDTO(Long id, String nombreCurso, String descripcionCurso, String valorCurso, String metodoPago) {
        this.id = id;
        this.nombreCurso = nombreCurso;
        this.descripcionCurso = descripcionCurso;
        this.valorCurso = valorCurso;
        this.metodoPago = metodoPago;

    }

}


