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
import reactor.core.publisher.Mono;

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
}
