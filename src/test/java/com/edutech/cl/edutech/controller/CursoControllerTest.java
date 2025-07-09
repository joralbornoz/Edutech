package com.edutech.cl.edutech.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.edutech.cl.edutech.model.Curso;
import com.edutech.cl.edutech.service.CursoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(CursoController.class)
public class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CursoService cursoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Curso curso;

    @BeforeEach
    void setUp(){
        curso = new Curso();
        curso.setCodigo("CS101");
        curso.setNombreCurso("Programacion en Java");
        curso.setDescripcionCurso("Curso de Java");
        curso.setValorCurso("1000");
    }
    @Test
    public void testGetAllCursos() throws Exception {
        when(cursoService.findAll()).thenReturn(List.of(curso));

        mockMvc.perform(get("/api/cursos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].codigo").value("CS101"))
                .andExpect(jsonPath("$[0].nombreCurso").value("Programacion en Java"))
                .andExpect(jsonPath("$[0].descripcionCurso").value("Curso de Java"))
                .andExpect(jsonPath("$[0].valorCurso").value("1000"));
    }
    @Test
    public void testGetCursoByCodigo() throws Exception {
        when(cursoService.findByCodigo("CS101")).thenReturn(curso);

        mockMvc.perform(get("/api/cursos/CS101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value("CS101"))
                .andExpect(jsonPath("$.nombreCurso").value("Programacion en Java"))
                .andExpect(jsonPath("$.descripcionCurso").value("Curso de Java"))
                .andExpect(jsonPath("$.valorCurso").value("1000"));
    }
    @Test
    public void testCreateCurso() throws Exception {
        when(cursoService.save(any(Curso.class))).thenReturn(curso);

        mockMvc.perform(post("/api/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(curso)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value("CS101"))
                .andExpect(jsonPath("$.nombreCurso").value("Programacion en Java"))
                .andExpect(jsonPath("$.descripcionCurso").value("Curso de Java"))
                .andExpect(jsonPath("$.valorCurso").value("1000"));
    }

    @Test
    public void testUpdateCurso() throws Exception {
        when(cursoService.save(curso)).thenReturn(curso);

        mockMvc.perform(put("/api/cursos/CS101")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(curso)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value("CS101"))
                .andExpect(jsonPath("$.nombreCurso").value("Programacion en Java"))
                .andExpect(jsonPath("$.descripcionCurso").value("Curso de Java"))
                .andExpect(jsonPath("$.valorCurso").value("1000"));
    }

    @Test
    public void testDeleteCurso() throws Exception {
        doNothing().when(cursoService).deleteByCodigo("CS101");

        mockMvc.perform(delete("/api/cursos/CS101"))
                .andExpect(status().isOk());

        verify(cursoService, times(1)).deleteByCodigo("CS101");
    }

}
