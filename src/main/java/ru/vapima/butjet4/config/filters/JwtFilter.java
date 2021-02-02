package ru.vapima.butjet4.config.filters;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.vapima.butjet4.service.impl.JwtService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Configuration
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
      String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String email = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
          email= jwtService.takeEmailFromToken(authorizationHeader);
            System.out.println("START FILTER, AUTH -> "+email);
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("START FILTER, AUTH CORRECT");
            String commaSeparatedListOfAuthorities = jwtService.takeStringAuthoritiesFromToken(authorizationHeader);
            System.out.println("commaSeparatedListOfAuthorities|||  "+commaSeparatedListOfAuthorities);
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(commaSeparatedListOfAuthorities);
            System.out.println("authorities|||  "+authorities);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            email, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
