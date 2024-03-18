package com.course.testing.integration;


import com.course.testing.domain.Usuario;
import com.course.testing.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Class Correspondiente a Usuario Controller Integration Test
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@Testcontainers
public class UsuarioControllerIT  {




    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
      this.usuarioRepository.deleteAll();
    }


    //Test Junit para crear usuario
    @Test
    @DisplayName("Test Junit Method para crear usuario")
    public void givenObjectUsuario_whenCreateUsuario_thenReturnObjectoUsuario() throws Exception {


        //given - preparo nuestros datos
        Usuario usuario = Usuario.builder()
                .firstName("david")
                .lastName("garcia")
                .email("manuonda@gmail.com")
                .build();



        //when - acciones a realizar para testing
        ResultActions response = mockMvc.perform(post("/api/v1/usuarios/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)));

        //then - verificamos la salida
        response.andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.firstName",
                        is(usuario.getFirstName())))
                .andExpect(jsonPath("$.lastName",
                        is(usuario.getLastName())))
                .andExpect(jsonPath("$.email",
                        is(usuario.getEmail())));
    }


    //Test Junit obtener todos los usuarios
    @Test
    @DisplayName("Test Junit Method listar usuarios")
    public void given_when_then() throws Exception {
        //given - preparo nuestros datos
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(Usuario.builder().firstName("david").lastName("garcia").email("david.garcia@gmail.com").build());
        usuarios.add(Usuario.builder().firstName("andres").lastName("garcia").email("andres.garcia@gmail.com").build());

        this.usuarioRepository.saveAll(usuarios);

        //when - acciones a realizar para testing
        ResultActions resultActions = mockMvc.perform(get("/api/v1/usuarios/listar")
                .contentType(MediaType.APPLICATION_JSON));

        //then - verificamos la salida
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(usuarios.size())));
    }

    //Test for
    @Test
    @DisplayName("Junit Method Test integration Get Usuario By Id")
    public void givenObjetoUsuario_whenGetUsuarioById_thenReturnObjectoUsuario() throws Exception {
        //given - preparo nuestros datos
        Usuario usuario = Usuario.builder()
                .firstName("david")
                .lastName("garcia")
                .email("david.garcia@gmail.com")
                .build();
        Usuario usuarioSaved = this.usuarioRepository.save(usuario);

        //when - acciones a realizar para testing
        ResultActions resultActions = this.mockMvc.perform(get("/api/v1/usuarios/{id}", usuario.getId())
                .contentType(MediaType.APPLICATION_JSON));


        //then - verificamos la salida
        resultActions.andDo(print())
                .andExpect(jsonPath("$.firstName", is(usuario.getFirstName())));
    }

    @Test
    @DisplayName("Test Junit Method Failed get usuario by id ")
    public void givenInvalidUsuarioId_whenGetByIdUsuario_thenReturnObjectEmpty() throws Exception {

        long usuarioId = 50L;
        Usuario usuario = Usuario.builder()
                .firstName("david")
                .lastName("garcia")
                .email("david.garcia@gmail.com")
                .build();
       this.usuarioRepository.save(usuario);


        //when - acciones a realizar para testing
        ResultActions resultActions = mockMvc.perform(get("/api/v1/usuarios/{id}", usuarioId));

        //then - verificamos la salida
        resultActions.andExpect(status().isNotFound())
                .andDo(print());
    }


    //Test for junit update Usuario
    @Test
    @DisplayName("Test Junit method actualizar usuario")
    public void givenObjectUsuario_whenUpdateUsuario_thenReturnObjectUsuario() throws Exception {
        //given - preparo nuestros datos
        Usuario usuarioSaved = Usuario.builder()
                .firstName("david")
                .lastName("garcia")
                .email("david.garcia@gmail.com")
                .build();

        Usuario usuario = this.usuarioRepository.save(usuarioSaved);

        Usuario usuarioUpdate = Usuario.builder()

                .firstName("andres")
                .lastName("gonzalez")
                .email("andres.gonzalez@gmail.com")
                .build();


        System.out.println("usuario" + usuarioSaved.getId());


        //when - acciones a realizar para testing
        ResultActions resultActions  = mockMvc.perform(put("/api/v1/usuarios/{id}", usuarioSaved.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioUpdate)));

        //then - verificamos la salida
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(usuarioUpdate.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(usuarioUpdate.getLastName())))
                .andExpect(jsonPath("$.email", is(usuarioUpdate.getEmail())));

    }


    //Test for junit update Usuario Rest Api- negative scenario
    @Test
    @DisplayName("Test Junit method actualizar usuario")
    public void givenObjectUpdateUsuario_whenUpdateUsuario_thenReturn404NotFound() throws Exception {
        //given - preparo nuestros datos
        Long idUsuario = 50L;
        Usuario usuarioSaved = Usuario.builder()
                .firstName("david")
                .lastName("garcia")
                .email("david.garcia@gmail.com")
                .build();

        Usuario usuario = this.usuarioRepository.save(usuarioSaved);

        Usuario usuarioUpdate = Usuario.builder()
                .firstName("andres")
                .lastName("gonzalez")
                .email("andres.gonzalez@gmail.com")
                .build();


        System.out.println("usuario" + usuarioSaved.getId());


        //when - acciones a realizar para testing
        ResultActions resultActions  = mockMvc.perform(put("/api/v1/usuarios/{id}", idUsuario)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioUpdate)));

        //then - verificamos la salida
        resultActions.andExpect(status().isNotFound())
                .andDo(print());
    }

    //Test for delete usuario by id Rest Api
    @Test
    @DisplayName("Test Delete Usuario by Id Rest API ")
    public void givenObjectUsuario_whenDeleteUsuarioById_thenReturn200() throws Exception {
        //given - preparo nuestros datos
        Usuario usuario = Usuario.builder()
                .firstName("david")
                .lastName("garcia")
                .email("david.garcia@gmail.com")
                .build();
        this.usuarioRepository.save(usuario);

        //when - acciones a realizar para testing
        ResultActions resultActions = this.mockMvc.perform(delete("/api/v1/usuarios/{id}", usuario.getId()));


        //then - verificamos la salida
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }




}
