package com.edutech.cl.edutech.repository;



import com.edutech.cl.edutech.dto.response.MetodoPagoDTO;
import com.edutech.cl.edutech.dto.response.MetodoPagoQueryDTO;
import com.edutech.cl.edutech.model.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long> {

    @Query("select new com.edutech.cl.edutech.dto.response.MetodoPagoDTO(mp)" +
            " from MetodoPago mp " +
            " where mp.curso.id = :idCurso ")
    List<MetodoPagoDTO> findByIdCurso(@Param("idCurso") Long idCurso);


    @Query(value = "select id, tipoPago, monto, numeroCuotas from metodoPago", nativeQuery = true)
    List<MetodoPagoQueryDTO> buscarSoloMetodoPagos();

}

