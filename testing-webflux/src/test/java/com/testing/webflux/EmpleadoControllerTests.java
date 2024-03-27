package com.testing.webflux;


import com.testing.webflux.controller.EmpleadoController;
import com.testing.webflux.dto.EmpleadoDTO;
import com.testing.webflux.entity.Empleado;
import com.testing.webflux.service.EmpleadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = EmpleadoController.class)
public class EmpleadoControllerTests {


    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private EmpleadoService empleadoService;


    //Test JUnit Method Saved Empleado
    @Test
    @DisplayName("Test Junit Method Guardar Empleado")
    public void givenEmpleadoObject_whenGuardaEmpleado_thenReturnObjectEmpleado() {

        //given - preparo nuestros datos
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setFirstName("david");
        empleadoDTO.setLastName("garcia");
        empleadoDTO.setEmail("manuonda@gmail.com");

        BDDMockito.given(this.empleadoService.guardarEmpleado(ArgumentMatchers.any(EmpleadoDTO.class)))
                .willReturn(Mono.just(empleadoDTO));

        //when - acciones a realizar para testing
         WebTestClient.ResponseSpec response =  webTestClient.post().uri("/api/empleados")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(empleadoDTO), EmpleadoDTO.class)
                .exchange();

        //then - verificamos la salida
        response.expectStatus().isCreated()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.firstName").isEqualTo(empleadoDTO.getFirstName())
                .jsonPath("$.lastName").isEqualTo(empleadoDTO.getLastName())
                .jsonPath("$.email").isEqualTo(empleadoDTO.getEmail());
    }

    //Test Junit Method get by id
    @Test
    @DisplayName("Test Junit Method Get By Id Empleado")
    public void givenObjectEmpleado_whenGetByIdEmpleado_thenReturnObjectEmpleado() {
        //given - preparo nuestros datos
        Long idEmpleado = 1L;
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setFirstName("david");
        empleadoDTO.setLastName("garcia");
        empleadoDTO.setEmail("manuonda@gmail.com");

        BDDMockito.given(this.empleadoService.buscarPorId(ArgumentMatchers.any()))
                .willReturn(Mono.just(empleadoDTO));

        //when - acciones a realizar para testing
        WebTestClient.ResponseSpec response =  webTestClient.get().uri("/api/empleados/{id}", Collections.singletonMap("id", idEmpleado))
                        .exchange();

        //then - verificamos la salida
        response.expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.firstName").isEqualTo(empleadoDTO.getFirstName())
                .jsonPath("$.lastName").isEqualTo(empleadoDTO.getLastName())
                .jsonPath("$.email").isEqualTo(empleadoDTO.getEmail());
    }

    @Test
    @DisplayName("Junit Method Test get All Empleados")
    public void givenListObjectEmpleado_whenfindListObjectEmpleado_returnListObjectEmpleados(){
        //given
        EmpleadoDTO emp1 = new EmpleadoDTO();
        emp1.setFirstName("david");
        emp1.setLastName("garcia");
        emp1.setEmail("david.garcia@gmail.com");

        EmpleadoDTO emp2 = new EmpleadoDTO();
        emp2.setFirstName("andres");
        emp2.setLastName("garcia");
        emp2.setEmail("andres.garcia@gmail.com");
        List<EmpleadoDTO> empleados = new ArrayList<>();
        empleados.add(emp1);
        empleados.add(emp2);

        Flux<EmpleadoDTO> fluxEmpleados = Flux.fromIterable(empleados);

        BDDMockito.given(this.empleadoService.getAllEmpleados()).willReturn(fluxEmpleados);

        //when
        WebTestClient.ResponseSpec response = webTestClient.get().uri("/api/empleados")
                .accept(MediaType.APPLICATION_JSON)
                .exchange();

        //then
        response.expectStatus().isOk()
                .expectBodyList(EmpleadoDTO.class)
                .consumeWith(System.out::println)
                .hasSize(empleados.size());

    }

    @Test
    @DisplayName("Test Junit Method Update Empleado")
    public void givenObjectEmpleado_whenUpdateEmpleado_thenReturnObjectEmpleado(){

      //given
        String idEmpleado ="1234";
      EmpleadoDTO empleado = new EmpleadoDTO();
      empleado.setFirstName("david");
      empleado.setLastName("garcia");
      empleado.setEmail("david.garcia@gmail.com");

      BDDMockito.given(this.empleadoService.actualizarEmpleado(ArgumentMatchers.any(EmpleadoDTO.class),
                      ArgumentMatchers.any(String.class)))
              .willReturn(Mono.just(empleado));

      //when - action or behavior
       WebTestClient.ResponseSpec responseSpec = webTestClient.put()
               .uri("/api/empleados/{id}", Collections.singletonMap("id",idEmpleado))
               .contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON)
               .body(Mono.just(empleado), EmpleadoDTO.class)
               .exchange();

        //then - verify the result or output
        responseSpec.expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.firstName").isEqualTo(empleado.getFirstName())
                .jsonPath("$.lastName").isEqualTo(empleado.getLastName());

    }

    //Test for
    @Test
    @DisplayName("Junit Method Delete by Id")
    public void givenEmpleadoId_whenDeleteEmpleadoById_thenReturnEmptyObject() {
        //given - preparo nuestros datos
        String idEmpleado = "1234";
        Mono<Void> voidMono = Mono.empty();

        BDDMockito.given(this.empleadoService.eliminarEmpleado(idEmpleado))
                .willReturn(voidMono);

        //when - acciones a realizar para testing
        WebTestClient.ResponseSpec response = webTestClient.delete().uri("/api/empleados/{id}", Collections.singletonMap("id",idEmpleado))
                .exchange();

        //then - verificamos la salida
        response.expectStatus().isNoContent()
                .expectBody()
                .consumeWith(System.out::println);
    }
}
