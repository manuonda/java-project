package com.testing.webflux.mapper;

import com.testing.webflux.dto.EmpleadoDTO;
import com.testing.webflux.entity.Empleado;

public class EmpleadoMapper {

    public static EmpleadoDTO mapToEmpleadoDTO(Empleado empleado){
        return new EmpleadoDTO(
                empleado.getId(),
                empleado.getFirstName(),
                empleado.getLastName(),
                empleado.getEmail()
        );
    }

    public static Empleado mapToEmpleado(EmpleadoDTO empleadoDTO){
        return new Empleado(
                empleadoDTO.getId(),
                empleadoDTO.getFirstName(),
                empleadoDTO.getLastName(),
                empleadoDTO.getEmail()
        );
    }
}
