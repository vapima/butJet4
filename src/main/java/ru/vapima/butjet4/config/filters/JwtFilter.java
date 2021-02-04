package ru.vapima.butjet4.config.filters;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.vapima.butjet4.config.CustomTokenAuthentication;
import ru.vapima.butjet4.service.JwtService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        Long id = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            email = jwtService.takeEmailFromToken(authorizationHeader);
            id = jwtService.takeIdFromToken(authorizationHeader);
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            String commaSeparatedListOfAuthorities = jwtService.takeStringAuthoritiesFromToken(authorizationHeader);
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(commaSeparatedListOfAuthorities);
            CustomTokenAuthentication customTokenAuthentication = new CustomTokenAuthentication(
                    email, id, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(customTokenAuthentication);

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
