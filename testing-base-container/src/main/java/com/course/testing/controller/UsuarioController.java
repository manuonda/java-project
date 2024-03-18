package com.course.testing.controller;


import com.course.testing.domain.Usuario;
import com.course.testing.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }



    @PostMapping("/guardar")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody @Valid Usuario usuario){
        Usuario usuarioSaved = this.usuarioService.guardar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSaved);
    }



    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        List<Usuario> usuarios = this.usuarioService.getAllUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }


    @GetMapping("{id}")
    public ResponseEntity<Usuario> getByIdUsuario(@PathVariable("id") Long id){
      return this.usuarioService.getUsuarioById(id)
              .map(ResponseEntity::ok)
              .orElseGet(()-> ResponseEntity.notFound().build());

    }


    @PutMapping("{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") Long id, @RequestBody @Valid Usuario usuario){
        return this.usuarioService.getUsuarioById(id)
                .map(savedEmployee -> {
                 savedEmployee.setFirstName(usuario.getFirstName());
                 savedEmployee.setLastName(usuario.getLastName());
                 savedEmployee.setEmail(usuario.getEmail());
                 Usuario updatedUsuario = this.usuarioService.updateUsuario(savedEmployee);
                 return  ResponseEntity.status(HttpStatus.OK).body(updatedUsuario);

                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable("id") Long idUsuario){
        this.usuarioService.deleteUsuarioById(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario delete");
    }


}
