package com.edutech.cl.edutech.controller;

import com.edutech.cl.edutech.model.Curso;
import com.edutech.cl.edutech.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Gabriel Ferrufino Rivera
 */
@RestController
@RequestMapping("/api/cursos")
@Tag(name = "Cursos", description = "Operaciones relacionadas con Cursos")

public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    @Operation(summary = "Obtener todos los cursos", description = "Obtiene lista de todo los cursos")
    public List<Curso> getAllCursos() {
        return cursoService.findAll();
    }



    @GetMapping("/{codigo}")
    @Operation(summary = "Obtiene datos de un curso",
            description = "Obtiene informacion de un curso por codigo")
    public Curso getCursoByCodigo(@PathVariable String codigo) {
        return cursoService.findByCodigo(codigo);
    }




    @PostMapping
    @Operation(summary = "Crea curso", description = "Crea informacion de un curso para ser adquerido")
    public Curso createCurso(@RequestBody Curso curso) {
        return cursoService.save(curso);
    }

    @PutMapping("/{codigo}")
    @Operation(summary = "Actualiza informacion de un curso", description = "Actualiza curso por id")
    public Curso updateCurso(@PathVariable String codigo, @RequestBody Curso curso) {
        curso.setCodigo(codigo);
        return cursoService.save(curso);
    }
    @DeleteMapping("/{codigo}")
    @Operation(summary = "Elimina un curso del sistema", description = "Eliminacion de curso por su codigo")
    public void deleteCurso(@PathVariable String codigo) {
        cursoService.deleteByCodigo(codigo);
    }
}