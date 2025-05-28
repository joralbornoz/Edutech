package com.edutech.cl.edutech.service;


import com.edutech.cl.edutech.dto.request.MetodoPagoRequestDTO;
import com.edutech.cl.edutech.dto.response.*;
import com.edutech.cl.edutech.model.Curso;
import com.edutech.cl.edutech.model.MetodoPago;
import com.edutech.cl.edutech.repository.CursoRepository;
import com.edutech.cl.edutech.repository.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
@Service
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public List<MetodoPagoQueryDTO> listarSoloMetodoPagos() {

        return metodoPagoRepository.buscarSoloMetodoPagos();

    }

    public String crearMetodoPago(Long cursoId, MetodoPagoRequestDTO metodoPagoRequestDTO) {
        Optional<Curso> cursoOpt = cursoRepository.findById(cursoId);
        if (!cursoOpt.isPresent()) {
            return "Curso no encontrado";
        }
        Curso curso = cursoOpt.get();

        // Validar el campo 'tipo' (debe venir en DTO)
        String tipoPago = metodoPagoRequestDTO.getTipoPago(); // Asegúrate de que este campo exista en DTO
        if (tipoPago == null || (!tipoPago.equalsIgnoreCase("credito") && !tipoPago.equalsIgnoreCase("debito"))) {
            return "El campo 'tipoPago' debe ser 'credito' o 'debito'.";
        }

        // Validar según tipo
        if (tipoPago.equalsIgnoreCase("debito")) {
            // Para débito, monto obligatorio, enCuotas y numeroCuotas no aplican
            if (metodoPagoRequestDTO.getMonto() == null) {
                return "El monto es obligatorio para método de pago débito.";
            }

        } else {
            // cuando es tarjeta Credito
            boolean enCuotas = metodoPagoRequestDTO.isEnCuotas();
            if (!enCuotas) {
                // Sin cuota: número de cuotas 0
                metodoPagoRequestDTO.setNumeroCuotas(0);
            } else {
                // Si enCuotas es true, verificar número
                if (metodoPagoRequestDTO.getNumeroCuotas() <= 0) {
                    return "Debe ingresar una cantidad válida de cuotas, mayor que 0.";
                }
            }
            // Validar monto
            if (metodoPagoRequestDTO.getMonto() == null) {
                return "El monto es obligatorio para método de pago crédito.";
            }
        }

        MetodoPago metodoPago = new MetodoPago();
        metodoPago.setTipoPago(metodoPagoRequestDTO.getTipoPago());
        metodoPago.setMonto(metodoPagoRequestDTO.getMonto());
        metodoPago.setEnCuotas(metodoPagoRequestDTO.isEnCuotas());
        metodoPago.setNumeroCuotas(metodoPagoRequestDTO.getNumeroCuotas());
        metodoPago.setCurso(curso);

        metodoPagoRepository.save(metodoPago);

        return "Pago creado correctamente.";
    }

    public String eliminarPago(Long metodoPagoId) {
        metodoPagoRepository.deleteById(metodoPagoId);
        return "Pago Eliminado";
    }
}
