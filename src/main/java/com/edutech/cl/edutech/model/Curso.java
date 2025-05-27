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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreCurso;

    @Column(nullable = false)
    private String descripcionCurso;

    @Column(nullable = false)
    private String valorCurso;

    @Column(nullable = false)
    private String metodoPago;

    @ManyToOne
    @JoinColumn(name = "id_cliente_fk", nullable = false)
    private Cliente cliente;




}
