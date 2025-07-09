package com.edutech.cl.edutech;

import com.edutech.cl.edutech.model.*;
import com.edutech.cl.edutech.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Generar cursos
        for (int i = 0; i < 5; i++) {
            Curso curso = new Curso();
            curso.setCodigo(faker.code().asin());
            curso.setNombreCurso(faker.educator().course());
            curso.setDescripcionCurso(faker.educator().course());
            curso.setValorCurso(String.valueOf(faker.number().numberBetween(10000, 150000 )));
            cursoRepository.save(curso);
        }

        List<Curso> cursos = cursoRepository.findAll();

        // Generar Cliente
        for (int i = 0; i < 50; i++) {
            Cliente cliente = new Cliente();
            cliente.setId(i + 1);
            cliente.setRut(faker.idNumber().valid());
            cliente.setNombre(faker.name().name());
            cliente.setApellido(faker.name().lastName());
            cliente.setCorreo(faker.internet().emailAddress());
            cliente.setNumTelefono(faker.number().numberBetween(100000000, 999999999));
            cliente.setCurso(cursos.get(random.nextInt(cursos.size())));
            clienteRepository.save(cliente);
        }
        List<Cliente> clientes = clienteRepository.findAll();

        // Generar MetodoPago
        for (int i = 0; i < 20; i++) {
            MetodoPago metodoPago = new MetodoPago();
            metodoPago.setId(i + 1);
            metodoPago.setCliente(clientes.get(random.nextInt(clientes.size())));
            metodoPago.setFechaCompra(new Date());
            String tipoPago = faker.options().option("Debito", "Credito");
            metodoPago.setTipoPago(tipoPago);
            metodoPago.setMonto(faker.number().numberBetween(10000, 150000));

            if ("Credito".equals(tipoPago)) {
                String enCuotas = faker.options().option("S", "N");
                metodoPago.setEnCuotas(enCuotas);
                if ("S".equals(enCuotas)) {
                    int numeroCuotas = faker.number().numberBetween(1, 36);
                    metodoPago.setNumeroCuotas(numeroCuotas);
                } else {
                    metodoPago.setNumeroCuotas(null);
                }
            } else {
                // Para Débito, no se establecen cuotas
                metodoPago.setEnCuotas(null);
                metodoPago.setNumeroCuotas(null);
            }

            metodoPagoRepository.save(metodoPago);
        }
    }

}
