package com.edutech.cl.edutech.service;

import com.edutech.cl.edutech.dto.request.CursoRequestDTO;
import com.edutech.cl.edutech.model.Cliente;
import com.edutech.cl.edutech.model.Curso;
import com.edutech.cl.edutech.repository.ClienteRepository;
import com.edutech.cl.edutech.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Repository
@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

   public String crearCurso(long clienteId, CursoRequestDTO cursoRequestDTO) {
       Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);
       if (!clienteOpt.isPresent()) {
           return "Cliente no encontrado";
       }
       Cliente cliente = clienteOpt.get();

       Curso curso = new Curso();
       curso.setNombreCurso(cursoRequestDTO.getNombreCurso());
       curso.setDescripcionCurso(cursoRequestDTO.getDescripcionCurso());
       curso.setValorCurso(cursoRequestDTO.getValorCurso());
       curso.setMetodoPago(cursoRequestDTO.getMetodoPago());
       curso.setCliente(cliente);

       cursoRepository.save(curso);

       return "OK";

   }
}
