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
         Mono<Empleado> empleado = this.empleadoRepository.findById(id);
         return empleado.map((entity) -> EmpleadoMapper.mapToEmpleadoDTO(entity));
    }

    @Override
    public Flux<EmpleadoDTO> getAllEmpleados() {
        Flux<Empleado> listado = this.empleadoRepository.findAll();
        return listado.map((entity) -> EmpleadoMapper.mapToEmpleadoDTO(entity))
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<EmpleadoDTO> actualizarEmpleado(EmpleadoDTO dto, String idEmpleado) {

        Mono<Empleado> empleadoMono = this.empleadoRepository.findById(idEmpleado);
        Mono<Empleado> updateEmpleado  = empleadoMono.flatMap((entity) -> {
               entity.setFirstName(dto.getFirstName());
               entity.setLastName(dto.getLastName());
               entity.setEmail(dto.getEmail());
               return empleadoRepository.save(entity);
        });

        return updateEmpleado.map(entity -> EmpleadoMapper.mapToEmpleadoDTO(entity));

    }

    @Override
    public Mono<Void> eliminarEmpleado(String idEmpleado) {
       return this.empleadoRepository.deleteById(idEmpleado);
    }

}
