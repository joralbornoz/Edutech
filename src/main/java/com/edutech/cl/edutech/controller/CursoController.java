package com.edutech.cl.edutech.controller;



import com.edutech.cl.edutech.dto.request.CursoRequestDTO;
import com.edutech.cl.edutech.dto.response.ClienteDTO;
import com.edutech.cl.edutech.dto.response.ClienteQueryDTO;
import com.edutech.cl.edutech.dto.response.CursoDTO;
import com.edutech.cl.edutech.dto.response.CursoQueryDTO;
import com.edutech.cl.edutech.model.Curso;
import com.edutech.cl.edutech.service.ClienteService;
import com.edutech.cl.edutech.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Gabriel Ferrufino Rivera
 */
@RestController
@RequestMapping("/api/v1/cursos")

public class CursoController {

    @Autowired
    private CursoService cursoService;
    @Autowired
    private ClienteService clienteService;


    @PostMapping("/crearCurso/{id}")
    public ResponseEntity<String> crearCurso(@PathVariable ("id") Long id, @RequestBody CursoRequestDTO cursoRequestDTO) {

        String mensaje = "";
        try{
            mensaje = cursoService.crearCurso(id, cursoRequestDTO);

            return ResponseEntity.status(HttpStatus.OK).body(mensaje);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CursoDTO>> listar() {
        List<CursoDTO> curso = cursoService.listar();
        if (curso.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(curso);
        }
        return ResponseEntity.ok(curso);

    }

    @GetMapping("/solo-cursos")
    public ResponseEntity<List<CursoQueryDTO>> listarSoloCursos() {
        List<CursoQueryDTO> soloCursos = cursoService.listarSoloCursos();

        return ResponseEntity.ok(soloCursos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCurso(@PathVariable Long id) {
        String mensaje = "";
        try{
            mensaje = cursoService.eliminarCurso(id);

            return ResponseEntity.status(HttpStatus.OK).body(mensaje);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
