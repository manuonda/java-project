package com.testing.webflux.service;

import com.testing.webflux.dto.EmpleadoDTO;
import com.testing.webflux.entity.Empleado;
import reactor.core.publisher.Mono;

public interface EmpleadoService {

    Mono<Empleado> guardarEmpleado(EmpleadoDTO empleadoDTO);

}
