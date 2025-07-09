package com.edutech.cl.edutech.service;


import com.edutech.cl.edutech.model.Curso;
import com.edutech.cl.edutech.repository.CursoRepository;
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

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public Curso findByCodigo(String codigo) {
        return cursoRepository.findById(codigo).orElse(null);
    }

    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void deleteByCodigo(String codigo) {
        cursoRepository.deleteById(codigo);
    }
}
