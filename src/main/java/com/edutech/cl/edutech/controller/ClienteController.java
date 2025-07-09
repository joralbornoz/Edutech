package com.edutech.cl.edutech.controller;

import com.edutech.cl.edutech.model.Cliente;
import com.edutech.cl.edutech.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Abraham Lopez Bastias
 */

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Operaciones relacionadas con Clientes")

public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Obtener todos los clientes", description = "Obtiene lista de todo los clientes")
    public List<Cliente> getAllClientes() {
        return clienteService.findAll();

    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene datos de un cliente", description = "Obtiene informacion de un cliente por id")
    public Cliente getClienteById(@PathVariable Integer id) {
        return clienteService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crea datos de un cliente", description = "Crea informacion de un cliente")
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza datos de un cliente", description = "Actualiza informacion de un cliente por id")
    public Cliente updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return clienteService.save(cliente);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un cliente", description = "Eliminacion de cliente por id")
    public void deleteClientes(@PathVariable Integer id){
            clienteService.deleteById(id);
    }
}


