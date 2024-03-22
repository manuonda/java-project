package com.testing.webflux.service;

import com.testing.webflux.dto.EmpleadoDTO;
import com.testing.webflux.entity.Empleado;
import com.testing.webflux.mapper.EmpleadoMapper;
import com.testing.webflux.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmpleadoServiceImpl implements  EmpleadoService{


    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public Mono<EmpleadoDTO> guardarEmpleado(EmpleadoDTO empleadoDTO) {
        Empleado empleado = EmpleadoMapper.mapToEmpleado(empleadoDTO);
        Mono<Empleado> savedEmpleado = this.empleadoRepository.save(empleado);
        return savedEmpleado.map((entity) -> EmpleadoMapper.mapToEmpleadoDTO(entity));
    }

    @Override
    public Mono<EmpleadoDTO> buscarPorId(String id) {
        return null;
    }

    @Override
    public Flux<EmpleadoDTO> getAllEmpleados() {
        return null;
    }

    @Override
    public Mono<EmpleadoDTO> actualizarEmpleado(EmpleadoDTO dto, String idEmpleado) {
        return null;
    }

    @Override
    public Mono<Void> eliminarEmpleado(String idEmpleado) {
        return null;
    }

}
