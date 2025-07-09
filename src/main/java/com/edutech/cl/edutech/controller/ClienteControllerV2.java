package com.edutech.cl.edutech.controller;


import com.edutech.cl.edutech.assemblers.ClienteModelAssembler;
import com.edutech.cl.edutech.model.Cliente;
import com.edutech.cl.edutech.service.ClienteService;
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
@RequestMapping("api/v2/clientes")
public class ClienteControllerV2 {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Cliente>> getAllCliente(){
        List<EntityModel<Cliente>> clientes = clienteService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(clientes,
            linkTo(methodOn(ClienteControllerV2.class).getAllCliente()).withSelfRel());
    }
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Cliente> getClienteById(@PathVariable Integer id) {
        Cliente cliente = clienteService.findById(id);
        return assembler.toModel(cliente);
    }
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Cliente>> createCliente(@RequestBody Cliente cliente){
        Cliente newCliente = clienteService.save(cliente);
        return ResponseEntity
                .created(linkTo(methodOn(ClienteControllerV2.class).getClienteById(newCliente.getId())).toUri())
                .body(assembler.toModel(newCliente));
    }
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Cliente>> updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente){
        cliente.setId(id);
        Cliente updatedCliente = clienteService.save(cliente);
        return ResponseEntity
                .ok(assembler.toModel(updatedCliente));
    }
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteCliente(@PathVariable Integer id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}

