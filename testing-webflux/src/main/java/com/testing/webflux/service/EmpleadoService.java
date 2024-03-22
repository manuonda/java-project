package com.testing.webflux.service;

import com.testing.webflux.dto.EmpleadoDTO;
import com.testing.webflux.entity.Empleado;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmpleadoService {


    /**
     * Funcion que permite guardar
     * un empleado
     * @param empleadoDTO
     * @return
     */
    Mono<EmpleadoDTO> guardarEmpleado(EmpleadoDTO empleadoDTO);
    
    Mono<EmpleadoDTO> buscarPorId(String id);
    
    Flux<EmpleadoDTO> getAllEmpleados();
    
    Mono<EmpleadoDTO> actualizarEmpleado(EmpleadoDTO dto, String idEmpleado);
    
    Mono<Void> eliminarEmpleado(String idEmpleado);

}
