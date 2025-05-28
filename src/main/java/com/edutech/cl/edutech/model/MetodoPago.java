package com.edutech.cl.edutech.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "MetodoPago")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class MetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String tipoPago;

    @Column(nullable = true)
    private double monto;

    @Column(nullable = true)
    private boolean enCuotas;

    @Column(nullable = true)
    private int numeroCuotas;


    @ManyToOne
    @JoinColumn(name = "id_curso_fk")
    private Curso curso;

}
