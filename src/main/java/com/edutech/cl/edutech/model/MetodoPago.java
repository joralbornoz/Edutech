package com.edutech.cl.edutech.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table (name = "MetodoPago")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class MetodoPago {

    @Id
    private Integer id;
    private Date fechaCompra;
    private String tipoPago;
    private Integer monto;
    private String enCuotas;
    private Integer numeroCuotas;


    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

}
