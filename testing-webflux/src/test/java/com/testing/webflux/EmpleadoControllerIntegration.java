package com.testing.webflux;


import com.testing.webflux.dto.EmpleadoDTO;
import com.testing.webflux.service.EmpleadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

}
