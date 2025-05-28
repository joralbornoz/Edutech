package com.edutech.cl.edutech.service;

import com.edutech.cl.edutech.dto.request.ClienteRequestDTO;
import com.edutech.cl.edutech.dto.response.ClienteDTO;
import com.edutech.cl.edutech.dto.response.ClienteQueryDTO;
import com.edutech.cl.edutech.model.Cliente;
import com.edutech.cl.edutech.repository.ClienteRepository;
import com.edutech.cl.edutech.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CursoRepository cursoRepository;


    public List<ClienteDTO> listar(){

        List<ClienteDTO> clienteDTO = clienteRepository.buscarTodos();
        clienteDTO.forEach(cliente -> {
            cliente.setCursoList(cursoRepository.findByIdCliente(cliente.getId()));
        });

        return clienteDTO;

    }


    public List<ClienteQueryDTO> listarSoloClientes(){

        return clienteRepository.buscarSoloClientes();

    }


    public String guardarCliente(ClienteRequestDTO clienteRequestDTO) {

        Cliente cliente = new Cliente();
        cliente.setRut(clienteRequestDTO.getRut());
        cliente.setNombre(clienteRequestDTO.getNombre());
        cliente.setApellido(clienteRequestDTO.getApellido());
        cliente.setCorreo(clienteRequestDTO.getCorreo());
        cliente.setNumTelefono(clienteRequestDTO.getNumTelefono());

        clienteRepository.save(cliente);

        return "OK";
    }


    public String actualizarCorreoCliente(ClienteRequestDTO clienteRequestDTO) {

        Optional<Cliente> cliente = clienteRepository.findById(clienteRequestDTO.getId());
        if(cliente.isPresent()){
            Cliente clienteActualizado = cliente.get();
            clienteActualizado.setCorreo(clienteRequestDTO.getCorreo());
            clienteRepository.save(clienteActualizado);
        }

        return "OK";
    }


    public String eliminarCliente(Long clienteId) {
        clienteRepository.deleteById(clienteId);
        return "OK";
    }
}
