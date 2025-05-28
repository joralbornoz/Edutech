package com.edutech.cl.edutech.dto.response;


import com.edutech.cl.edutech.model.MetodoPago;
import lombok.Data;

@Data
public class MetodoPagoDTO {

    private Long id;
    private String tipoPago;
    private double monto;
    private boolean enCuotas;
    private int numeroCuotas;

    public MetodoPagoDTO(MetodoPago metodoPago) {
        this.id = metodoPago.getId();
        this.tipoPago = metodoPago.getTipoPago();
        this.monto = metodoPago.getMonto();
        this.enCuotas = metodoPago.isEnCuotas();
        this.numeroCuotas = metodoPago.getNumeroCuotas();

    }

    public MetodoPagoDTO(Long id, String tipoPago, double monto, boolean enCuotas, int numeroCuotas) {
        this.id = id;
        this.tipoPago = tipoPago;
        this.monto = monto;
        this.enCuotas = enCuotas;
        this.numeroCuotas = numeroCuotas;

    }
}
