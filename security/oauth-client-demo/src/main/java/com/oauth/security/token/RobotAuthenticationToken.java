package com.oauth.security.token;


import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;


// Authentication

// public class RobotAuthenticationToken implements AUthentication

public class RobotAuthenticationToken  extends AbstractAuthenticationToken{ 

    public RobotAuthenticationToken(){
        super(AuthorityUtils.createAuthorityList("ROLE_robot"));
    }


    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return "Ms. Robot";
    }


    @Override
    public boolean isAuthenticated() {
        return super.isAuthenticated();
    }


    @Override
    public void setAuthenticated(boolean authenticated) {
        throw new RuntimeException("Can't not touch this");
    }

    
}
