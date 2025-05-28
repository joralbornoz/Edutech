package com.edutech.cl.edutech.dto.request;


import lombok.Data;

@Data
public class CursoRequestDTO {


    private Long idCurso;
    private String nombreCurso;
    private String descripcionCurso;
    private String valorCurso;
    private String metodoPago;

}
