package com.hellokoding.auth.security;

import com.hellokoding.auth.service.UserService;
import com.hellokoding.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SecurityFilter extends GenericFilterBean {

    @Autowired
    private UserService userService;

    public SecurityFilter(UserService userService) {
        this.userService =userService;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String token = servletRequest.getParameter("token");
        if (token != null && token.length() > 0){
            User user = userService.get(token);
            if (user != null){
                final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                List<String> roles = userService.getRoles(token);
                roles.forEach(r -> {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(r);
                    grantedAuthorities.add(grantedAuthority);
                });
                Authentication authentication = new UsernamePasswordAuthenticationToken(user, token, grantedAuthorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


}
