package com.edutech.cl.edutech.repository;

import com.edutech.cl.edutech.dto.response.CursoDTO;
import com.edutech.cl.edutech.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query("select mew com.edutech.cl.edutech.dto.response.CursoDTO(a)" +
            "from Curso cu" +
            " where cu.cliente.id = idCliente")
    List<CursoDTO> findAllCursos(@Param("idCliente") Long idCliente);
}
