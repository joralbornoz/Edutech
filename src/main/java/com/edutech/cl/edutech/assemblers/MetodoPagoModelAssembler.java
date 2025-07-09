package com.edutech.cl.edutech.assemblers;

import com.edutech.cl.edutech.controller.ClienteControllerV2;
import com.edutech.cl.edutech.controller.MetodoPagoControllerV2;
import com.edutech.cl.edutech.model.MetodoPago;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Component
public class MetodoPagoModelAssembler implements RepresentationModelAssembler <MetodoPago, EntityModel<MetodoPago>>{


    @Override
    public EntityModel<MetodoPago> toModel(MetodoPago metodoPago) {
        return EntityModel.of(metodoPago,
                linkTo(methodOn(MetodoPagoControllerV2.class).getMetodoPagoById(metodoPago.getId())).withSelfRel(),
                linkTo(methodOn(MetodoPagoControllerV2.class).getAllMetodoPago()).withRel("Listar los metodos de Pago"),
                // Enlace para actualizar el método de pago
                linkTo(methodOn(MetodoPagoControllerV2.class).updateMetodoPago(metodoPago.getId(), null)).withRel("actualizar").withType("PUT"),
                // Enlace para eliminar el método de pago
                linkTo(methodOn(MetodoPagoControllerV2.class).deleteMetodoPago(metodoPago.getId())).withRel("eliminar").withType("DELETE"),
                // Enlace a la información del cliente relacionada
                linkTo(methodOn(ClienteControllerV2.class).getClienteById(metodoPago.getCliente().getId())).withRel("cliente"));
    }
}
