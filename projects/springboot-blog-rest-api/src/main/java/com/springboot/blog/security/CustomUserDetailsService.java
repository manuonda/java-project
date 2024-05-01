package com.springboot.blog.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.User;
import com.springboot.blog.exception.ResourceNotFound;
import com.springboot.blog.repository.UserRepository;

/**
 * este código implementa un servicio personalizado (CustomUserDetailsService)
 * para cargar detalles de usuario para la autenticación y la autorización en
 * Spring Security. Utiliza un UserRepository para buscar un usuario por su
 * nombre de usuario, recupera los roles asignados a ese usuario y los devuelve
 * en un objeto UserDetails que puede ser utilizado por Spring Security para la
 * autenticación y autorización de usuarios.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // Constructor to inject the UserRepository dependency
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Override the loadUserByUsername method from UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find the user by username in the repository, or throw an exception if not
        // found
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFound("User not found with username : " + username));

        // Get Authorities (Roles assigned to the user)
        Set<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        // Create and return a UserDetails object with user information
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), // Username (in this case, email)
                user.getPassword(), // User's password
                authorities // Authorities (roles) assigned to the user
        );
    }

}
