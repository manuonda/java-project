package com.course.testing.service;


import com.course.testing.domain.Usuario;
import com.course.testing.exception.ResourceNotFoundException;
import com.course.testing.repository.UsuarioRepository;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTests {
    @Mock //objeto simulado de la clase repository
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;
    // private UsuarioService usuarioService;


    private Usuario usuario;

    @BeforeEach
    public void setup(){
        System.out.println("setup");
       // usuarioRepository = Mockito.mock(UsuarioRepository.class);
        // usuarioService = new UsuarioServiceImpl(usuarioRepository);

        usuario = Usuario.builder()
                .id(1L)
                .firstName("david")
                .lastName("garcia")
                .build();

    }

    //Test Junit para guardar Usuario
    @Test
    @DisplayName("Guardar Usuario")
    public void givenUsuarioObjeto_whenGuardamosUsuario_thenReturnUsuarioObjeto()  {
        //given - preparo nuestros datos

        BDDMockito.given(usuarioRepository.save(usuario)).willReturn(usuario);

        //when - acciones a realizar para testing
        Usuario usuarioSaved = usuarioService.guardar(usuario);

        //then - verificamos la salida
        Assertions.assertThat(usuarioSaved).isNotNull();
    }


    //Test Junit para guardar Usuario
    @Test
    @DisplayName("Exception al guardar el usuario , existe el email ")
    public void givenExistingEmail_whenGuardamosUsuario_thenThrowsException()  {
        //given - preparo nuestros datos
        BDDMockito.given(usuarioRepository.findByEmail(usuario.getEmail()))
                        .willReturn(Optional.of(usuario));

        // No llega a ejecutarse  BDDMockito.given(usuarioRepository.save(usuario)).willReturn(usuario);
        //when - acciones a realizar para testing
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class,() -> {
           usuarioService.guardar(usuario);
        });

        //then  : nunca se ejecuta el llamado al repositorio por la exception
        verify(this.usuarioRepository, never()).save(any(Usuario.class));
    }


    //Test Junit para obtener todos los usuarios
    @Test
    @DisplayName("Junit Test para obtener todos los usuarios")
    public void givenUsuariosList_whenFindAllUsuarios_thenReturnListUsuarios() {
        //given - preparo nuestros datos
        Usuario usuario2 = Usuario.builder()
                .id(2L)
                .firstName("andres")
                .lastName("garcia")
                .build();

        BDDMockito.given(this.usuarioRepository.findAll()).willReturn(List.of(usuario, usuario2));
        //when - acciones a realizar para testing
        List<Usuario> listUsuarios = this.usuarioService.getAllUsuarios();

        //then - verificamos la salida
        Assertions.assertThat(listUsuarios).isNotNull();
        Assertions.assertThat(listUsuarios.size()).isEqualTo(2);

        verify(this.usuarioRepository).findAll();

    }

    //Test Junit retorna listado vacio de usuarios
    @Test
    @DisplayName("Junit test obtiene listado vacio de usuarios")
    public void givenVacioListadoUsuario_whenFindAllUsuario_thenReturnListadoVacioUsuarios() {

        //given - preparo nuestros datos
        BDDMockito.given(this.usuarioRepository.findAll()).willReturn(Collections.emptyList());

        //when - acciones a realizar para testing
        List<Usuario> listUsuarios = this.usuarioService.getAllUsuarios();

        //then - verificamos la salida
        Assertions.assertThat(listUsuarios).isEmpty();
        Assertions.assertThat(listUsuarios.size()).isEqualTo(0);

        verify(this.usuarioRepository).findAll();
    }


    // Junit Test methdo para buscar empleado by Id
    @Test
    @DisplayName("Junit Test obtiene un usuario por el id de Usuario")
    public void givenUsuario_whenBuscarPorIdUsuario_returnObjectUsuario(){
        //given
        BDDMockito.given(this.usuarioRepository.findById(anyLong())).willReturn(Optional.of(this.usuario));

        //when
        Usuario usuarioFind = this.usuarioService.getUsuarioById(usuario.getId()).get();

        //then
        Assertions.assertThat(usuarioFind).isNotNull();
       Assertions.assertThat(usuarioFind.getId()).isGreaterThan(0);
    }



    // Junit Test para actualizar un usuario
    @Test
    @DisplayName("Junit Test actualizar usuario")
    public void  givenUsuarioObject_whenUpdateUsuario_thenReturnObjectUsuario(){

        //given
        //Establecemos el comportamiento de findById
        BDDMockito.given(this.usuarioRepository.findById(any())).willReturn(Optional.of(usuario));
        BDDMockito.given(this.usuarioRepository.save(any())).willReturn(usuario);
        usuario.setEmail("correo.prueba@gmail.com");
        usuario.setFirstName("andres");
        usuario.setLastName("roman");

        //when
        Usuario usuarioActualizado = this.usuarioService.updateUsuario(usuario);

        //then
        Assertions.assertThat(usuario.getEmail()).isEqualTo("correo.prueba@gmail.com");
        Assertions.assertThat(usuario.getFirstName()).isEqualTo("andres");
        Assertions.assertThat(usuario.getLastName()).isEqualTo("roman");
    }


    //Junit Testa para eliminar un usuario
    @Test
    @DisplayName("Junit Test para eliminar usuario")
    public void givenObjectUsuario_whenDeleteUsuario_returnEmptyObject(){
        //given
        BDDMockito.given(this.usuarioRepository.findById(usuario.getId())).willReturn(Optional.of(usuario));

        //when
        usuarioService.deleteUsuarioById(usuario.getId());

        //then
        verify(usuarioRepository, times(1)).delete(usuario);
    }


    //Junit Test id no existe al eliminar el usuario - debe lanzar exception
    @Test
    @DisplayName("Junit Test id no existe al eliminar el usuario")
    public void givenUsuarioNoExiste_whenDeleteUsuario_thenThrowsResourceNotFoundException(){

        //given
        BDDMockito.given(this.usuarioRepository.findById(any())).willReturn(Optional.empty());

        //when
        org.junit.jupiter.api.Assertions.assertThrows( ResourceNotFoundException.class, () -> {
            usuarioService.deleteUsuarioById(usuario.getId());
        }, "No existe el id del usuario a eliminar ");

        //then
        verify(usuarioRepository, never()).delete(any(Usuario.class));
    }

}
