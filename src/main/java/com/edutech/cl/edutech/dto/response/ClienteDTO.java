package com.edutech.cl.edutech.dto.response;


import com.edutech.cl.edutech.model.Cliente;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ClienteDTO {

    private Long id;
    private String rut;
    private String nombre;
    private String apellido;
    private String correo;
    private String numTelefono;
    List<CursoDTO> cursoList = new ArrayList<>();

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.rut = cliente.getRut();
        this.nombre = cliente.getNombre();
        this.apellido = cliente.getApellido();
        this.correo = cliente.getCorreo();
        this.numTelefono = cliente.getNumTelefono();

    }

    public ClienteDTO(Long id, String rut, String nombre, String apellido, String correo, String numTelefono) {
        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.numTelefono = numTelefono;

    }

}
