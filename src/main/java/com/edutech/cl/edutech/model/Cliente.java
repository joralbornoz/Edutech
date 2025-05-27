package com.edutech.cl.edutech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 13, nullable = false)
    private String rut;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String numTelefono;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private Date fechaNacimiento;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    List<Curso> cursoList = new ArrayList<>();

}
