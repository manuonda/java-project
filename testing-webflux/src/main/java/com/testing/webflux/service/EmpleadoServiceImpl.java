package com.testing.webflux.service;

import com.testing.webflux.dto.EmpleadoDTO;
import com.testing.webflux.entity.Empleado;
import com.testing.webflux.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EmpleadoServiceImpl implements  EmpleadoService{


    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public Mono<Empleado> guardarEmpleado(EmpleadoDTO empleadoDTO) {
        return null;
    }
}
