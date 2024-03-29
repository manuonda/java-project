package com.testing.webflux;


import com.testing.webflux.dto.EmpleadoDTO;
import com.testing.webflux.entity.Empleado;
import com.testing.webflux.service.EmpleadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Class correspondiente a Test de Integration
 * usando TestContainers.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "3600000")
public class EmpleadoControllerIntegration extends AbstractContainerBaseTest {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private WebTestClient webTestClient;




    @Test
    @DisplayName("Test Junit Method save Empleado")
    public void givenObjectEmpleado_whenGuardaEmpleado_thenReturnObjectEmpleado(){

        //given
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setFirstName("david");
        empleadoDTO.setLastName("garcia");
        empleadoDTO.setEmail("david.garcia@gmail.com");

        //when
        WebTestClient.ResponseSpec response = webTestClient.post().uri("/api/empleados")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(empleadoDTO), EmpleadoDTO.class)
                .exchange();


        //then
         response.expectStatus().isCreated()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.firstName").isEqualTo(empleadoDTO.getFirstName())
                 .jsonPath("$.lastName").isEqualTo(empleadoDTO.getLastName())
                 .jsonPath("$.email").isEqualTo(empleadoDTO.getEmail()) ;

    }


    //Test for
    @Test
    @DisplayName("Test Junit Method Get Empleado")
    public void given_when_then() {
        //given - preparo nuestros datos
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setFirstName("david");
        empleadoDTO.setLastName("garcia");
        empleadoDTO.setEmail("david.garcia@gmail.com");

        EmpleadoDTO empleadoDTO1 = this.empleadoService.guardarEmpleado(empleadoDTO).block();

        //when - acciones a realizar para testing

        WebTestClient.ResponseSpec response = this.webTestClient.get()
                .uri("/api/empleados/{id}", Collections.singletonMap("id", empleadoDTO1.getId()))
                .exchange();

        //then - verificamos la salida
        response.expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.firstName").isEqualTo(empleadoDTO.getFirstName())
                .jsonPath("$.lastName").isEqualTo(empleadoDTO.getLastName());


    }

    //Test Junit Method Get All Empleados
    @Test
    @DisplayName("Junit Method Get All Empleados")
    public void givenObjectEmpleados_whenGetAllEmpleados_thenReturnObjectListEmpleados() {
        //given - preparo nuestros datos
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setFirstName("david");
        empleadoDTO.setLastName("garcia");
        empleadoDTO.setEmail("david.garcia@gmail.com");
        this.empleadoService.guardarEmpleado(empleadoDTO);

        EmpleadoDTO emp2 = new EmpleadoDTO();
        emp2.setFirstName("david");
        emp2.setLastName("garcia");
        emp2.setEmail("david.garcia@gmail.com");
        this.empleadoService.guardarEmpleado(emp2);


        //when - acciones a realizar para testing
        WebTestClient.ResponseSpec responseSpec = webTestClient.get()
                .uri("/api/empleados")
                .exchange();

        //then - verificamos la salida
        responseSpec.expectStatus()
                .isOk()
                .expectBodyList(EmpleadoDTO.class)
                .consumeWith(System.out::println);
    }


    //Test for
    @Test
    @DisplayName("Junit Method Update Empleado")
    public void givenObjectEmpleado_whenUpdateEmpleado_thenReturnObjectEmpleado() {
        //given - preparo nuestros datos
        EmpleadoDTO emp1= new EmpleadoDTO();
        emp1.setFirstName("david");
        emp1.setLastName("garcia");
        emp1.setEmail("david.garcia@gmail.com");
        EmpleadoDTO empleadoSaved  = this.empleadoService.guardarEmpleado(emp1).block();

        EmpleadoDTO empleadoUpdated = new EmpleadoDTO();
        empleadoUpdated.setFirstName("andres");
        empleadoUpdated.setLastName("garcia");
        empleadoUpdated.setEmail("andres.garcia@gmail.com");


        //when - acciones a realizar para testing

        WebTestClient.ResponseSpec responseSpec = webTestClient.put()
                .uri("/api/empleados/{id}",Collections.singletonMap("id",empleadoSaved.getId().toString()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(empleadoUpdated), EmpleadoDTO.class)
                .exchange();

        //then - verificamos la salida
        responseSpec.expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.firstName").isEqualTo(empleadoUpdated.getFirstName())
                .jsonPath("$.lastName").isEqualTo(empleadoUpdated.getLastName())
                .jsonPath("$.email").isEqualTo(empleadoUpdated.getEmail());
    }


    //Test for
    @Test
    @DisplayName("Junit Method Teste delete Empleado")
    public void givenObjectEmpleado_when_then() {
        //given - preparo nuestros datos
        EmpleadoDTO emp1= new EmpleadoDTO();
        emp1.setFirstName("david");
        emp1.setLastName("garcia");
        emp1.setEmail("david.garcia@gmail.com");
        EmpleadoDTO empleadoSaved  = this.empleadoService.guardarEmpleado(emp1).block();

        //when - acciones a realizar para testing
         WebTestClient.ResponseSpec responseSpec = this.webTestClient.delete()
                 .uri("/api/empleados/{id}",
                         Collections.singletonMap("id", empleadoSaved.getId()))
                 .exchange();

        //then - verificamos la salida
        responseSpec.expectStatus().isNoContent()
                .expectBody()
                .consumeWith(System.out::println);
    }

}
