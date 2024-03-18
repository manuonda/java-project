package com.course.testing.service;

import com.course.testing.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario guardar(Usuario usuario);

    List<Usuario> getAllUsuarios();

    Optional<Usuario> getUsuarioById(Long id);

    Usuario updateUsuario(Usuario usuario);

    void deleteUsuarioById(Long id);

}
