package com.edutech.cl.edutech.assemblers;

import com.edutech.cl.edutech.controller.CursoControllerV2;
import com.edutech.cl.edutech.model.Curso;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CursoModelAssembler implements RepresentationModelAssembler<Curso, EntityModel<Curso>> {

    @Override
    public EntityModel<Curso> toModel(Curso curso) {
        return EntityModel.of(curso,
                linkTo(methodOn(CursoControllerV2.class).getCursoByCodigo(curso.getCodigo())).withSelfRel(),
                linkTo(methodOn(CursoControllerV2.class).getAllCursos()).withRel("cursos"));
    }
}

