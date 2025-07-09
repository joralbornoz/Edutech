package com.edutech.cl.edutech.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.edutech.cl.edutech.controller.ClienteControllerV2;
import com.edutech.cl.edutech.model.Cliente;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {

    @Override
    public EntityModel<Cliente> toModel(Cliente cliente) {
        return EntityModel.of(cliente,
                linkTo(methodOn(ClienteControllerV2.class).getClienteById(cliente.getId())).withSelfRel(),
                linkTo(methodOn(ClienteControllerV2.class).getAllCliente()).withRel("clientes"));
    }


}
