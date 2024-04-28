package com.springboot.blog.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Role;
import com.springboot.blog.entity.User;
import com.springboot.blog.exception.GlobalExceptionHandler;
import com.springboot.blog.payload.LoginDTO;
import com.springboot.blog.service.AuthService;
import com.springboot.blog.payload.RegisterDTO;
import com.springboot.blog.repository.RoleRepository;
import com.springboot.blog.repository.UserRepository;

/**
 * este código implementa un servicio de autenticación que utiliza Spring
 * Security para autenticar a los usuarios utilizando un nombre de usuario (o
 * correo electrónico) y una contraseña proporcionados, y luego establece la
 * identidad del usuario autenticado en el contexto de seguridad de la
 * aplicación.
 */
@Service
public class AuthServiceImpl implements AuthService {

    /**
     * Esta es una instancia de AuthenticationManager,
     * que es una interfaz de Spring Security utilizada para autenticar a los
     * usuarios.
     */
    private AuthenticationManager authenticationManager;

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    public AuthServiceImpl(AuthenticationManager authenticationManager,
    UserRepository userRepository, RoleRepository roleRepository,
    PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDTO login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getUsernameOrEmail(), login.getPassword()));

        /**
         * Una vez que el usuario ha sido autenticado con éxito, se establece el objeto
         * Authentication en el contexto de seguridad utilizando SecurityContextHolder.
         * Esto significa que el usuario ha iniciado sesión y su identidad está
         * disponible en el contexto de seguridad para su uso posterior en la
         * aplicación.
         */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "User Logged-in successfully!";
    }

    @Override
    public String register(RegisterDTO registerDTO) {
        boolean existUsername = this.userRepository.existsByUsername(registerDTO.getUsername());
        if( existUsername) {
            throw new GlobalExceptionHandler("Username exists");
        }
        boolean existsEmails = this.userRepository.existsByEmail(registerDTO.getEmail());
        if(existsEmails){
            throw new GlobalExceptionHandler("Email exists");
        }

        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setUsername(registerDTO.getUsername());
        user.setName(registerDTO.getName());

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER")
        .orElseThrow(() -> new GlobalExceptionHandler("Role no exists"));
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "Use registered successfully";




    }
}
