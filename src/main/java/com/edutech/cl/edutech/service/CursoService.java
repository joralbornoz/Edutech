package com.edutech.cl.edutech.service;

import com.edutech.cl.edutech.dto.request.CursoRequestDTO;
import com.edutech.cl.edutech.dto.response.ClienteDTO;
import com.edutech.cl.edutech.dto.response.ClienteQueryDTO;
import com.edutech.cl.edutech.dto.response.CursoDTO;
import com.edutech.cl.edutech.dto.response.CursoQueryDTO;
import com.edutech.cl.edutech.model.Cliente;
import com.edutech.cl.edutech.model.Curso;
import com.edutech.cl.edutech.repository.ClienteRepository;
import com.edutech.cl.edutech.repository.CursoRepository;
import com.edutech.cl.edutech.repository.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Repository
@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private MetodoPagoRepository metodoPagoRepository;


    public List<CursoDTO> listar(){

        List<CursoDTO> cursoDTO = cursoRepository.buscarTodos();
        cursoDTO.forEach(curso -> {
            curso.setMetodoPagoList(metodoPagoRepository.findByIdCurso(curso.getId()));
        });

        return cursoDTO;

    }

    public List<CursoQueryDTO> listarSoloCursos(){

        return cursoRepository.buscarSoloCursos();

    }


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

       return "Se agrega Curso con exito";

   }

    public String eliminarCurso(Long cursoId) {
        cursoRepository.deleteById(cursoId);
        return "Se elimina Curso con exito";
    }
}
