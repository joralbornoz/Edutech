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

    @Query("SELECT distinct new com.edutech.cl.edutech.dto.response.ClienteDTO(c.id, p.nombre, p.apellido)"+
    "FROM Cliente c" +
    "left join Curso cu on c.id = cu.cliente.id")
    List<ClienteDTO> BuscarTodos();

    @Query(value = "select id, apellido, correo from cliente", nativeQuery = true)
    List<ClienteQueryDTO> buscarSoloCliente();

}
