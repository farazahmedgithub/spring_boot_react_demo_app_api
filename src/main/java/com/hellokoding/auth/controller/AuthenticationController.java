package com.hellokoding.auth.controller;

import com.hellokoding.auth.model.Role;
import com.hellokoding.auth.model.User;
import com.hellokoding.auth.repository.RoleRepository;
import com.hellokoding.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public  User login(@RequestBody User user) throws IllegalArgumentException {

         User userR = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());

        if (userR == null){
            throw new IllegalArgumentException();
        }

        if (userR.getAuthToken()==null){
            userR.setAuthToken(UUID.randomUUID().toString());
            userR = userRepository.save(userR);

        }
        userR.setRoles(null);
        return userR;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    public  User register(@RequestBody User user) throws IllegalArgumentException {

        User userR = userRepository.findByUsername(user.getUsername());

        if (userR != null){
            throw new IllegalArgumentException();
        }
        userR = new User();

       userR.setUsername(user.getUsername());
        userR.setPassword(user.getPassword());
        userR.setPasswordConfirm(user.getPasswordConfirm());
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName("ROLE_USER");
        roles.add(role);
        userR.setRoles(roles);
        userR = userRepository.save(userR);
        userR.setRoles(null);
        return userR;
    }

    @ExceptionHandler
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
