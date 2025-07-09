package com.edutech.cl.edutech.controller;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import com.edutech.cl.edutech.model.MetodoPago;
import com.edutech.cl.edutech.service.MetodoPagoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

@WebMvcTest(MetodoPagoController.class)
public class MetodoPagoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MetodoPagoService metodoPagoService;

    @Autowired
    private ObjectMapper objectMapper;

    private MetodoPago metodoPago;

    @BeforeEach
    void setUp(){
        metodoPago = new MetodoPago();
        metodoPago.setId(1);
        metodoPago.setFechaCompra(new Date()); // Puedes ajustar si quieres una fecha fija
        metodoPago.setTipoPago("Credito");
        metodoPago.setMonto(500);
        metodoPago.setEnCuotas("No");
        metodoPago.setNumeroCuotas(null);
    }

    @Test
    public void allmetodopagos() throws Exception {
        when(metodoPagoService.findAll()).thenReturn(List.of(metodoPago));

        mockMvc.perform(get("/api/metodoPago"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].tipoPago").value("Credito"))
                .andExpect(jsonPath("$[0].monto").value(500))
                .andExpect(jsonPath("$[0].enCuotas").value("No"));
    }

    @Test
    public void getmetodopagobyid() throws Exception {
        when(metodoPagoService.findById(1)).thenReturn(metodoPago);

        mockMvc.perform(get("/api/metodoPago/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.tipoPago").value("Credito"))
                .andExpect(jsonPath("$.monto").value(500));
    }

    @Test
    public void createmethodopago() throws Exception {
        when(metodoPagoService.save(any(MetodoPago.class))).thenReturn(metodoPago);

        mockMvc.perform(post("/api/metodoPago")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(metodoPago)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.tipoPago").value("Credito"))
                .andExpect(jsonPath("$.monto").value(500));
    }

    @Test
    public void updatemethodopago() throws Exception {
        when(metodoPagoService.save(any(MetodoPago.class))).thenReturn(metodoPago);

        mockMvc.perform(put("/api/metodoPago/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(metodoPago)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.tipoPago").value("Credito"))
                .andExpect(jsonPath("$.monto").value(500));
    }

    @Test
    public void deletemetodopago() throws Exception {
        // No devuelve nada, solo verificamos la llamada
        doNothing().when(metodoPagoService).deleteById(1);

        mockMvc.perform(delete("/api/metodoPago/1"))
                .andExpect(status().isOk());

        verify(metodoPagoService, times(1)).deleteById(1);
    }
}


