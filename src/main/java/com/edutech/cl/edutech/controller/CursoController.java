package com.edutech.cl.edutech.controller;



import com.edutech.cl.edutech.dto.request.CursoRequestDTO;
import com.edutech.cl.edutech.dto.response.CursoDTO;
import com.edutech.cl.edutech.service.ClienteService;
import com.edutech.cl.edutech.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/Cursos")

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


}
