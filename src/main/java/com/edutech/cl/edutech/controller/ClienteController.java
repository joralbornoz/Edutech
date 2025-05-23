package com.edutech.cl.edutech.controller;


import com.edutech.cl.edutech.model.Cliente;
import com.edutech.cl.edutech.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = clienteService.findAll();
        if (clientes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientes);

    }

    @PostMapping
    public ResponseEntity<Cliente> guardar(@RequestBody Cliente cliente) {
        Cliente clienteNuevo = clienteService.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        try{
            Cliente cliente = clienteService.findById(id);
            return ResponseEntity.ok(cliente);
        } catch (Exception e){
            return ResponseEntity.notFound().build();

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        try{
            Cliente cli = clienteService.findById(id);
            cli.setId(id);
            cli.setRut(cliente.getRut());
            cli.setNombre(cliente.getNombre());
            cli.setApellido(cliente.getApellido());
            cli.setCorreo(cliente.getCorreo());
            cli.setFechaNacimiento(cliente.getFechaNacimiento());

            clienteService.save(cli);
            return ResponseEntity.ok(cliente);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> eliminar(@PathVariable Long id) {
        try{
            clienteService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}

