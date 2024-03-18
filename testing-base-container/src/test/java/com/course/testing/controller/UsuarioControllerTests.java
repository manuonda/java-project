package com.course.testing.controller;


import com.course.testing.domain.Usuario;
import com.course.testing.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//json path
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//get , post

//print
import static  org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

// any
import static org.mockito.ArgumentMatchers.any;

// given
import static org.mockito.BDDMockito.given;

// is
import static  org.hamcrest.CoreMatchers.is;






@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTests {


    @Autowired
    private MockMvc mockMvc;

    //injectamos el servicio usuario en la clase UsuarioController
    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;


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

        //Usamos mockito para emular el comportamiento del service de usuario
         given(this.usuarioService.guardar(any(Usuario.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        //when - acciones a realizar para testing
        ResultActions response = mockMvc.perform(post("/api/v1/usuarios/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)));

        //then - verificamos la salida
        response.andExpect(status().isCreated())
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
        given(this.usuarioService.getAllUsuarios()).willReturn(usuarios);

        //when - acciones a realizar para testing
        ResultActions resultActions = mockMvc.perform(get("/api/v1/usuarios/listar")
                .contentType(MediaType.APPLICATION_JSON));

        //then - verificamos la salida
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(usuarios.size())));
    }

    //Test Junit Method get usuario by Id
    @Test
    @DisplayName("Test Junit Method get usuario by id ")
    public void givenObjectUsuario_whenGetByIdUsuario_thenReturnObjectUsuario() throws Exception {

        Long idUsuario = 1L;
        //given - preparo nuestros datos
        Usuario usuario = Usuario.builder()
                .firstName("david")
                .lastName("garcia")
                .email("david.garcia@gmail.com")
                .build();

        given(this.usuarioService.getUsuarioById(anyLong())).willReturn(Optional.of(usuario));

        //when - acciones a realizar para testing
        ResultActions resultActions = mockMvc.perform(get("/api/v1/usuarios/{id}", idUsuario)
                .contentType(MediaType.APPLICATION_JSON));

        //then - verificamos la salida
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName",
                        is(usuario.getFirstName())))
                .andExpect(jsonPath("$.lastName",
                        is(usuario.getLastName())))
                .andExpect(jsonPath("$.email",
                        is(usuario.getEmail()))) ;
    }


    //Test Junit Failed usuario no existe by id
    @Test
    @DisplayName("Test Junit Method Failed get usuario by id ")
    public void givenInvalidUsuarioId_whenGetByIdUsuario_thenReturnObjectEmpty() throws Exception {

        Long idUsuario = 1L;

        given(this.usuarioService.getUsuarioById(anyLong())).willReturn(Optional.empty());

        //when - acciones a realizar para testing
        ResultActions resultActions = mockMvc.perform(get("/api/v1/usuarios/{id}", idUsuario)
                .contentType(MediaType.APPLICATION_JSON));

        //then - verificamos la salida
        resultActions.andExpect(status().isNotFound())
                .andDo(print());
    }


    //Test for junit update Usuario
    @Test
    @DisplayName("Test Junit method actualizar usuario")
    public void givenObjectUsuario_whenUpdateUsuario_thenReturnObjectUsuario() throws Exception {
        //given - preparo nuestros datos
        Long idUsuario  =1L;
        Usuario usuario = Usuario.builder()
                .firstName("david")
                .lastName("garcia")
                .email("david.garcia@gmail.com")
                .build();

        Usuario usuarioUpdate = Usuario.builder()

                .firstName("andres")
                .lastName("gonzalez")
                .email("andres.gonzalez@gmail.com")
                .build();

        given(usuarioService.getUsuarioById(idUsuario)).willReturn(Optional.of(usuario));
        given(usuarioService.updateUsuario(any(Usuario.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));


        //when - acciones a realizar para testing
        ResultActions resultActions  = mockMvc.perform(put("/api/v1/usuarios/{id}", idUsuario)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioUpdate)));

        //then - verificamos la salida
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(usuarioUpdate.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(usuarioUpdate.getLastName())))
                .andExpect(jsonPath("$.email", is(usuarioUpdate.getEmail())));

    }



    //Test for junit update Usuario - negative scenario
    @Test
    @DisplayName("Test Junit method actualizar usuario - negative scenario")
    public void givenObjectUsuario_whenUpdateUsuario_thenReturn404() throws Exception {
        //given - preparo nuestros datos
        Long idUsuario  =1L;
        Usuario usuario = Usuario.builder()
                .firstName("david")
                .lastName("garcia")
                .email("david.garcia@gmail.com")
                .build();

        Usuario usuarioUpdate = Usuario.builder()

                .firstName("andres")
                .lastName("gonzalez")
                .email("andres.gonzalez@gmail.com")
                .build();

        given(usuarioService.getUsuarioById(idUsuario)).willReturn(Optional.empty());
        given(usuarioService.updateUsuario(any(Usuario.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));


        //when - acciones a realizar para testing
        ResultActions resultActions  = mockMvc.perform(put("/api/v1/usuarios/{id}", idUsuario)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioUpdate)));

        //then - verificamos la salida
        resultActions.andExpect(status().isNotFound())
                .andDo(print());
    }

    //Test for delete usuario by id
    @Test
    @DisplayName("Test Delete Usuario by Id")
    public void givenObjectUsuario_whenDeleteUsuarioById_thenReturn200() throws Exception {
        //given - preparo nuestros datos
         Long idUsuario =1L;
         willDoNothing().given(usuarioService).deleteUsuarioById(idUsuario);

        //when - acciones a realizar para testing
        ResultActions resultActions = this.mockMvc.perform(delete("/api/v1/usuarios/{id}",idUsuario));

        //then - verificamos la salida
        resultActions.andExpect(status().isOk())
                .andDo(print());
    }





}
