package com.edutech.cl.edutech.repository;

import com.edutech.cl.edutech.dto.response.ClienteDTO;
import com.edutech.cl.edutech.dto.response.ClienteQueryDTO;
import com.edutech.cl.edutech.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT distinct new com.edutech.cl.edutech.dto.response.ClienteDTO(c.id, c.rut, c.nombre, c.apellido, c.correo, c.numTelefono) " +
            "FROM Cliente c " +
            "left join Curso cu on c.id = cu.cliente.id")
    List<ClienteDTO> buscarTodos();

    @Query(value = "select id, nombre, apellido, correo from cliente", nativeQuery = true)
    List<ClienteQueryDTO> buscarSoloClientes();

}
