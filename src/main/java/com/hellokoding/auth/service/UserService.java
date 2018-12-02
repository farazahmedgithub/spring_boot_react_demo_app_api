package com.hellokoding.auth.service;

import com.hellokoding.auth.model.User;
import com.hellokoding.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User get(String token) {
        User user = userRepository.findByAuthToken(token);
        user.setRoles(user.getRoles());
        return user;
    }

    public List<String> getRoles(String token) {
        User user = userRepository.findByAuthToken(token);
        return user.getRoles().parallelStream().map(r -> r.getName()).collect(Collectors.toList());

    }
}
