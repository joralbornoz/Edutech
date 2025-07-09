package com.edutech.cl.edutech.controller;

import com.edutech.cl.edutech.assemblers.MetodoPagoModelAssembler;
import com.edutech.cl.edutech.model.MetodoPago;
import com.edutech.cl.edutech.service.MetodoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("api/v2/metodopago")
public class MetodoPagoControllerV2 {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @Autowired
    private MetodoPagoModelAssembler assembler;

    @GetMapping(produces =MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<MetodoPago>> getAllMetodoPago(){
        List<EntityModel<MetodoPago>> metodosPago = metodoPagoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(metodosPago,
                linkTo(methodOn(MetodoPagoControllerV2.class).getAllMetodoPago()).withSelfRel());
    }
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<MetodoPago> getMetodoPagoById(@PathVariable Integer id) {
        MetodoPago metodoPago = metodoPagoService.findById(id);
        return assembler.toModel(metodoPago);
    }
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MetodoPago>> createMetodoPago(@RequestBody MetodoPago metodoPago){
        MetodoPago newMetodoPago = metodoPagoService.save(metodoPago);
        return ResponseEntity
                .created(linkTo(methodOn(MetodoPagoControllerV2.class).getMetodoPagoById(newMetodoPago.getId())).toUri())
                .body(assembler.toModel(newMetodoPago));
    }
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MetodoPago>> updateMetodoPago(@PathVariable Integer id, @RequestBody MetodoPago metodoPago){
        metodoPago.setId(id);
        MetodoPago updatedMetodoPago = metodoPagoService.save(metodoPago);
        return ResponseEntity
                .ok(assembler.toModel(updatedMetodoPago));
    }
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteMetodoPago(@PathVariable Integer id) {
        metodoPagoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
