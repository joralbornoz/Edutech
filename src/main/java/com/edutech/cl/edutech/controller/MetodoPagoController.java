package com.edutech.cl.edutech.controller;


import com.edutech.cl.edutech.dto.request.CursoRequestDTO;
import com.edutech.cl.edutech.dto.request.MetodoPagoRequestDTO;
import com.edutech.cl.edutech.dto.response.*;
import com.edutech.cl.edutech.service.CursoService;
import com.edutech.cl.edutech.service.MetodoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/metodoPago")
public class MetodoPagoController {

    @Autowired
    MetodoPagoService metodoPagoService;

    @Autowired
    private CursoService cursoService;

    @PostMapping("/crearMetodoPago/{id}")
    public ResponseEntity<String> crearMetodoPago(@PathVariable("id") Long id, @RequestBody MetodoPagoRequestDTO metodoPagoRequestDTO) {

        String mensaje = "";
        try{
            mensaje = metodoPagoService.crearMetodoPago(id, metodoPagoRequestDTO);

            return ResponseEntity.status(HttpStatus.OK).body(mensaje);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/solo-metodoPagos")
    public ResponseEntity<List<MetodoPagoQueryDTO>> listarSoloMetodoPagos() {
        List<MetodoPagoQueryDTO> soloMetodoPagos = metodoPagoService.listarSoloMetodoPagos();

        return ResponseEntity.ok(soloMetodoPagos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPago(@PathVariable Long id) {
        String mensaje = "";
        try{
            mensaje = metodoPagoService.eliminarPago(id);

            return ResponseEntity.status(HttpStatus.OK).body(mensaje);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
