package com.oauth.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public record UserDTO(String userName, String email, Collection<GrantedAuthority> authorities) {

}