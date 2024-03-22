package com.testing.webflux.controller;


import com.testing.webflux.dto.EmpleadoDTO;
import com.testing.webflux.service.EmpleadoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/empleados")
@AllArgsConstructor
public class EmpleadoController {


  private  EmpleadoService empleadoService;


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<EmpleadoDTO> guardarEmpleado(@RequestBody EmpleadoDTO empleadoDTO){
    return empleadoService.guardarEmpleado(empleadoDTO);
  }


  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<EmpleadoDTO> findById(@PathVariable("id") String idEmpleado){
    return empleadoService.buscarPorId(idEmpleado);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Flux<EmpleadoDTO> findAll(){
    return this.empleadoService.getAllEmpleados();
  }


  @PutMapping("{id}")
  public Mono<EmpleadoDTO> update(@PathVariable("id") String idEmpleado, @RequestBody EmpleadoDTO dto){
    return this.empleadoService.actualizarEmpleado(dto, idEmpleado);
  }

  @DeleteMapping("{id}")
  public Mono<Void> delete(@PathVariable("id") String idEmpleado){
    return this.empleadoService.eliminarEmpleado(idEmpleado);
  }


}
