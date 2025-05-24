package com.edutech.cl.edutech.dto.request;

import lombok.Data;


@Data
public class ClienteRequestDTO {

    private Long id;
    private String rut;
    private String nombre;
    private String apellido;
    private String correo;

}
