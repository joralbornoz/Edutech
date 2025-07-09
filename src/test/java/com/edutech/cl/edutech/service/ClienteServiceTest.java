package com.edutech.cl.edutech.service;

import com.edutech.cl.edutech.model.Cliente;
import com.edutech.cl.edutech.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;

    @Test
    public void testFindAll() {
        // Crear un cliente de ejemplo
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setRut("12345678-9");
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setCorreo("juan.perez@example.com");
        cliente.setNumTelefono(987654321);

        // Configurar el mock para devolver una lista con ese cliente
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        // Ejecutar el método
        List<Cliente> clientes = clienteService.findAll();

        // Verificaciones
        assertNotNull(clientes);
        assertEquals(1, clientes.size());
        assertEquals(cliente.getId(), clientes.get(0).getId());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setRut("12345678-9");
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setCorreo("juan.perez@example.com");
        cliente.setNumTelefono(987654321);

        // Configurar el mock para devolver el cliente cuando se busque por id
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        // Ejecutar
        Cliente resultado = clienteService.findById(id);

        // Verificación
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
    }

    @Test
    public void testFindById_NotFound() {
        Integer id = 999;
        // Configurar que devuelva vacío
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        // Ejecutar
        Cliente resultado = clienteService.findById(id);

        // Verificar que sea null
        assertNull(resultado);
    }

    @Test
    public void testSave() {
        Cliente cliente = new Cliente();
        cliente.setId(2);
        cliente.setRut("98765432-1");
        cliente.setNombre("Maria");
        cliente.setApellido("Lopez");
        cliente.setCorreo("maria.lopez@example.com");
        cliente.setNumTelefono(912345678);

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        // Ejecutar
        Cliente resultado = clienteService.save(cliente);

        // Verificaciones
        assertNotNull(resultado);
        assertEquals(cliente.getId(), resultado.getId());
        assertEquals("Maria", resultado.getNombre());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;

        // Configurar que deleteById no hace nada
        doNothing().when(clienteRepository).deleteById(id);

        // Ejecutar
        clienteService.deleteById(id);

        // Verificación
        verify(clienteRepository, times(1)).deleteById(id);
    }
}

