package com.edutech.cl.edutech.repository;

import com.edutech.cl.edutech.dto.response.ClienteDTO;
import com.edutech.cl.edutech.dto.response.ClienteQueryDTO;
import com.edutech.cl.edutech.dto.response.CursoDTO;
import com.edutech.cl.edutech.dto.response.CursoQueryDTO;
import com.edutech.cl.edutech.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query("select new com.edutech.cl.edutech.dto.response.CursoDTO(cu)" +
            " from Curso cu " +
            " where cu.cliente.id = :idCliente ")
    List<CursoDTO> findByIdCliente(@Param("idCliente") Long idCliente);

    @Query("SELECT distinct new com.edutech.cl.edutech.dto.response.CursoDTO(cu.id, cu.nombreCurso, cu.descripcionCurso, cu.valorCurso, cu.metodoPago) " +
            "FROM Curso cu " +
            "left join MetodoPago mp on cu.id = mp.curso.id")
    List<CursoDTO> buscarTodos();

    @Query(value = "select id, nombreCurso, descripcionCurso, valorCurso from curso", nativeQuery = true)
    List<CursoQueryDTO> buscarSoloCursos();

}
