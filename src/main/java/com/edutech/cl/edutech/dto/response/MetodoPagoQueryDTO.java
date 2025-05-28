package com.edutech.cl.edutech.dto.response;

import java.math.BigDecimal;

public interface MetodoPagoQueryDTO {

    public Long getId();
    public String getTipoPago();
    public Double getMonto();
    public int getNumeroCuotas();

}

