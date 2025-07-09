package com.edutech.cl.edutech.service;

import com.edutech.cl.edutech.model.MetodoPago;
import com.edutech.cl.edutech.repository.MetodoPagoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@SpringBootTest
@ActiveProfiles("test")
public class MetodoPagoTest {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @MockBean
    private MetodoPagoRepository metodoPagoRepository;

    @Test
    public void testFindAll() {
        // Crear un método de pago de ejemplo
        MetodoPago metodoPago = new MetodoPago();
        metodoPago.setId(1);
        metodoPago.setFechaCompra(new Date());
        metodoPago.setTipoPago("Credito");
        metodoPago.setMonto(10000);
        metodoPago.setEnCuotas("Sí");
        metodoPago.setNumeroCuotas(3);

        // Configurar mock para devolver una lista con ese método
        when(metodoPagoRepository.findAll()).thenReturn(List.of(metodoPago));

        // Ejecutar
        List<MetodoPago> metodos = metodoPagoService.findAll();

        // Verificaciones
        assertNotNull(metodos);
        assertEquals(1, metodos.size());
        assertEquals(metodoPago.getId(), metodos.get(0).getId());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        MetodoPago metodoPago = new MetodoPago();
        metodoPago.setId(id);
        metodoPago.setFechaCompra(new Date());
        metodoPago.setTipoPago("Debito");
        metodoPago.setMonto(20000);
        metodoPago.setEnCuotas("No");
        metodoPago.setNumeroCuotas(0);

        when(metodoPagoRepository.findById(id)).thenReturn(Optional.of(metodoPago));

        // Ejecutar
        MetodoPago resultado = metodoPagoService.findById(id);

        // Verificación
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
    }

    @Test
    public void testFindById_NotFound() {
        Integer id = 999;
        when(metodoPagoRepository.findById(id)).thenReturn(Optional.empty());

        // Ejecutar
        MetodoPago resultado = metodoPagoService.findById(id);

        // Verificar null
        assertNull(resultado);
    }

    @Test
    public void testSave() {
        MetodoPago metodoPago = new MetodoPago();
        metodoPago.setId(2);
        metodoPago.setFechaCompra(new Date());
        metodoPago.setTipoPago("Debito");
        metodoPago.setMonto(5000);
        metodoPago.setEnCuotas("No");
        metodoPago.setNumeroCuotas(0);

        when(metodoPagoRepository.save(metodoPago)).thenReturn(metodoPago);

        // Ejecutar
        MetodoPago resultado = metodoPagoService.save(metodoPago);

        // Verificación
        assertNotNull(resultado);
        assertEquals(metodoPago.getId(), resultado.getId());
        assertEquals("Debito", resultado.getTipoPago());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;
        doNothing().when(metodoPagoRepository).deleteById(id);

        // Ejecutar
        metodoPagoService.deleteById(id);

        // Verificar
        verify(metodoPagoRepository, times(1)).deleteById(id);
    }
}

