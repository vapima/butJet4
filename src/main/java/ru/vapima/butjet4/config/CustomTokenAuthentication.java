package ru.vapima.butjet4.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomTokenAuthentication extends UsernamePasswordAuthenticationToken {

    private final Long id;

    public CustomTokenAuthentication(Object principal, Long id, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
