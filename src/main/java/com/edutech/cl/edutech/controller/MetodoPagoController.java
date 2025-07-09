package com.edutech.cl.edutech.controller;



import com.edutech.cl.edutech.model.MetodoPago;
import com.edutech.cl.edutech.service.MetodoPagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.net.ResponseCache;
import java.util.List;


/**
 * Jorge Albornoz Morales
 */
@RestController
@RequestMapping("/api/metodoPago")
@Tag(name = "Metedo Pago", description = "Operaciones relacionadas con el Metodo de Pago")
public class MetodoPagoController {

    @Autowired
    MetodoPagoService metodoPagoService;

    @GetMapping
    @Operation(summary = "Obtener todos los Pagos", description = "Obtiene lista de los pagos realizados")
    @ApiResponse(responseCode = "200", description = "Obtencion de datos exitoso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MetodoPago.class)))
    @ApiResponse(responseCode = "404", description = "Pagos o datos no encontados")
    public List<MetodoPago> getAllMetodoPagos() {
        return metodoPagoService.findAll();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtener pago unico", description = "Obtiene informacion de pago mediante su id")
    public MetodoPago getMetodoPagoaById(@PathVariable Integer id) {
        return metodoPagoService.findById(id);
    }
    @PostMapping
    @Operation(summary = "Compra un curso disponible en sistema", description = "Compra curso y realiza pago en sistema")
    public MetodoPago createMetodoPago(@RequestBody MetodoPago metodoPago) {
        return metodoPagoService.save(metodoPago);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza informacion de pago", description = "Actualiza pago en sistema mediante su id")
    @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MetodoPago.class)))
    public MetodoPago updateMetodoPago(@PathVariable Integer id, @RequestBody MetodoPago metodoPago) {
        metodoPago.setId(id);
        return metodoPagoService.save(metodoPago);
    }
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar pago de curso",
            description = "Elimina un pago de curso en el sistema mediante su ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pago eliminado exitosamente",
                    content =  @Content(mediaType = "application/json",
                    schema =   @Schema(implementation = MetodoPago.class)
            )),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pago no encontrado")
    })
    public void deleteMetodoPago(@Parameter(description = "ID del pago a eliminar", required = true)
                                 @PathVariable Integer id) {
        metodoPagoService.deleteById(id);
    }
}