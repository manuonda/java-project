package com.testing.webflux.repository;

import com.testing.webflux.entity.Empleado;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EmpleadoRepository extends ReactiveCrudRepository<Empleado, String> {

}
