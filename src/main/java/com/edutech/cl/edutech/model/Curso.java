package com.edutech.cl.edutech.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Curso")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Curso {


    @Id
    private String codigo;
    private String nombreCurso;
    private String descripcionCurso;
    private String valorCurso;
}
