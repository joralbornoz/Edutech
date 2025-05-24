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
    private Date fechaNacimiento;
    List<CursoDTO> cursoList = new ArrayList<>();

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.rut = cliente.getRut();
        this.nombre = cliente.getNombre();
        this.apellido = cliente.getApellido();
        this.correo = cliente.getCorreo();
        this.numTelefono = cliente.getNumTelefono();
        this.fechaNacimiento = cliente.getFechaNacimiento();

    }

    public ClienteDTO(Long id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;

    }

}
