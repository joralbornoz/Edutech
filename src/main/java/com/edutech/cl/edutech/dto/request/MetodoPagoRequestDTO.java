package com.edutech.cl.edutech.dto.request;


import lombok.Data;

@Data
public class MetodoPagoRequestDTO {

    private Long idMetodo;
    private String tipoPago;
    private Double monto;
    private boolean enCuotas;
    private int numeroCuotas;
}
