package com.edutech.cl.edutech.controller;


import com.edutech.cl.edutech.dto.request.ClienteRequestDTO;
import com.edutech.cl.edutech.dto.response.ClienteDTO;
import com.edutech.cl.edutech.dto.response.ClienteQueryDTO;
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

    @GetMapping("/listar")
    public ResponseEntity<List<ClienteDTO>> listar() {
        List<ClienteDTO> clientes = clienteService.listar();
        if (clientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(clientes);
        }
        return ResponseEntity.ok(clientes);

    }

    @GetMapping("/solo-clientes")
    public ResponseEntity<List<ClienteQueryDTO>> listarSoloClientes(){
        List<ClienteQueryDTO> soloClientes = clienteService.listarSoloClientes();

        return ResponseEntity.ok(soloClientes);
    }
    @PostMapping
    public ResponseEntity<String> guardarCliente(@RequestBody ClienteRequestDTO clienteRequestDTO) {

        String mensaje = "";
        try{
            mensaje = clienteService.guardarCliente(clienteRequestDTO);

            return ResponseEntity.status(HttpStatus.OK).body(mensaje);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarCorreoCliente(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        String mensaje = "";
        try{
            mensaje = clienteService.actualizarCorreoCliente(clienteRequestDTO);

            return ResponseEntity.status(HttpStatus.OK).body(mensaje);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Long id) {
        String mensaje = "";
        try{
            mensaje = clienteService.eliminarCliente(id);

            return ResponseEntity.status(HttpStatus.OK).body(mensaje);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

