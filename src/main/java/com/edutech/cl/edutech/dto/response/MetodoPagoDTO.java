package com.edutech.cl.edutech.dto.response;


import com.edutech.cl.edutech.model.MetodoPago;
import lombok.Data;

@Data
public class MetodoPagoDTO {

    private Long id;
    private String tipoPago;
    private Double monto;
    private boolean enCuotas;
    private int numeroCuotas;

    public MetodoPagoDTO(MetodoPago metodoPago) {
        this.id = metodoPago.getId();
        this.tipoPago = metodoPago.getTipoPago();
        this.enCuotas = metodoPago.isEnCuotas();
        this.numeroCuotas = metodoPago.getNumeroCuotas();

    }
}
