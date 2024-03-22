package com.testing.webflux.controller;


import com.testing.webflux.dto.EmpleadoDTO;
import com.testing.webflux.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {


  @Autowired
  private  EmpleadoService empleadoService;


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<EmpleadoDTO> guardarEmpleado(@RequestBody EmpleadoDTO empleadoDTO){
    return empleadoService.guardarEmpleado(empleadoDTO);
  }
}
