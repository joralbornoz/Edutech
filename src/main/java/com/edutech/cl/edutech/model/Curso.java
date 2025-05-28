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

    @Column(nullable = true)
    private String nombreCurso;

    @Column(nullable = true)
    private String descripcionCurso;

    @Column(nullable = true)
    private String valorCurso;

    @Column(nullable = true)
    private String metodoPago;

    @ManyToOne
    @JoinColumn(name = "id_cliente_fk")
    private Cliente cliente;




}
