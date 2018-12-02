package com.hellokoding.auth.security;

import com.hellokoding.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Configuration
//@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().httpBasic().disable()
        .addFilterBefore(new SecurityFilter(userService), BasicAuthenticationFilter.class);
        //http.addFilterBefore(new SecurityFilter(), SecurityContextPersistenceFilter.class) ;
      //  http.addFilterBefore(new AuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class).
        //        addFilterBefore(new ManagementEndpointAuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class);
    }
}
