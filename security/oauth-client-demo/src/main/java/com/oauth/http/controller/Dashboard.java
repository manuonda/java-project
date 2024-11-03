package com.oauth.http.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.proc.SecurityContext;
import com.oauth.dto.UserDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class Dashboard {

  

    @GetMapping
    public String indexDashboard(Authentication authentication,Model model) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        
        var userName="";
        Collection<GrantedAuthority> authorities = null;
        var email ="";
        if ( authentication != null && authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();
            if ( principal instanceof UserDetails userDetails){
                 userName = userDetails.getUsername();
                 authorities =  new ArrayList<>(userDetails.getAuthorities());
            } else if ( principal instanceof OAuth2User oAuth2User){
                userName = oAuth2User.getAttribute("name");
                email = oAuth2User.getAttribute("email");
                authorities =  new ArrayList<>(oAuth2User.getAuthorities());

            }
        }

        var userDTO = new UserDTO(userName, email, authorities);

        model.addAttribute("usuario", userDTO);
        return  "index";
    }
    
}
