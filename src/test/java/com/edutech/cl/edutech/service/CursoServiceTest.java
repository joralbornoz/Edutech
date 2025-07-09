package com.edutech.cl.edutech.service;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.edutech.cl.edutech.model.Curso;
import com.edutech.cl.edutech.repository.CursoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;


import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class CursoServiceTest {

    @Autowired
    private CursoService cursoService;

    @MockBean
    private CursoRepository cursoRepository;

    @Test
    public void testFindAll() {
        // Crea un curso de ejemplo
        Curso curso = new Curso("C001", "Matemáticas", "Curso de matemáticas básicas", "100");
        // Configura el mock para devolver una lista con ese curso
        when(cursoRepository.findAll()).thenReturn(List.of(curso));

        // Llama al método real del servicio
        List<Curso> cursos = cursoService.findAll();

        // Verifica los resultados
        assertNotNull(cursos);
        assertEquals(1, cursos.size());
        assertEquals(curso.getCodigo(), cursos.get(0).getCodigo());
    }

    @Test
    public void testFindByCodigo() {
        String codigo = "C001";
        Curso curso = new Curso(codigo, "Matemáticas", "Curso de matemáticas básicas", "100");
        // Configura el mock para devolver el curso cuando se busque por código
        when(cursoRepository.findById(codigo)).thenReturn(Optional.of(curso));

        // Ejecuta el método
        Curso resultado = cursoService.findByCodigo(codigo);

        // Verificación
        assertNotNull(resultado);
        assertEquals(codigo, resultado.getCodigo());
    }

    @Test
    public void testFindByCodigo_NotFound() {
        String codigo = "C999";
        // Cuando no encuentre el código, devuelve vacío
        when(cursoRepository.findById(codigo)).thenReturn(Optional.empty());

        // Ejecuta
        Curso resultado = cursoService.findByCodigo(codigo);

        // Verifica que sea null
        assertNull(resultado);
    }

    @Test
    public void testSave() {
        Curso curso = new Curso("C002", "Historia", "Historia general", "150");
        when(cursoRepository.save(curso)).thenReturn(curso);

        // Ejecuta
        Curso resultado = cursoService.save(curso);

        // Verificación
        assertNotNull(resultado);
        assertEquals("C002", resultado.getCodigo());
        assertEquals("Historia", resultado.getNombreCurso());
    }

    @Test
    public void testDeleteByCodigo() {
        String codigo = "C001";

        // Configura que deleteById no hace nada
        doNothing().when(cursoRepository).deleteById(codigo);

        // Ejecuta
        cursoService.deleteByCodigo(codigo);

        // Verifica que se llame una sola vez
        verify(cursoRepository, times(1)).deleteById(codigo);
    }
}