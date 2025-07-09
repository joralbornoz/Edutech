package com.edutech.cl.edutech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cliente {

    @Id
    private Integer id;
    private String rut;
    private String nombre;
    private String apellido;
    private String correo;
    private Integer numTelefono;

    @ManyToOne
    @JoinColumn(name = "codigo_curso", nullable = false)
    private Curso curso;

}
