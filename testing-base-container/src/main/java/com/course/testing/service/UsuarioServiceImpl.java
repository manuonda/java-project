package com.course.testing.service;


import com.course.testing.domain.Usuario;
import com.course.testing.exception.ResourceNotFoundException;
import com.course.testing.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{


    //@Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public Usuario guardar(Usuario usuario)  {
        Optional<Usuario> usuarioGuardado = this.usuarioRepository.findByEmail(usuario.getEmail());
        if( usuarioGuardado.isPresent()){
            throw new ResourceNotFoundException("Usuario encontrado por el email" + usuario.getEmail());
        }

        return this.usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {

        return this.usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> getUsuarioById(Long id) {
        return this.usuarioRepository.findById(id);
    }

    @Override
    public Usuario updateUsuario(Usuario usuario ) {
        Optional<Usuario> findUsuario = this.usuarioRepository.findById(usuario.getId());
        if ( findUsuario.isEmpty()){
            throw new ResourceNotFoundException("Usuario no existe con el id : " +usuario.getId());
        }

        Usuario usuarioUpdate = findUsuario.get();
        usuarioUpdate.setFirstName(usuario.getFirstName());
        usuarioUpdate.setLastName(usuario.getLastName());
        usuarioUpdate.setEmail(usuario.getEmail());
        return this.usuarioRepository.save(usuarioUpdate);

    }

    @Override
    public void deleteUsuarioById(Long id) {
       Optional<Usuario>  findUsuario = this.usuarioRepository.findById(id);
       if( findUsuario.isEmpty()){
           throw new ResourceNotFoundException("Usuario no existe con el id : " + id);
       }
       this.usuarioRepository.delete(findUsuario.get());
    }

}
