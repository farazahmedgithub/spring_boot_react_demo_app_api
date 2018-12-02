package com.hellokoding.auth.controller;

import com.hellokoding.auth.model.Data;
import com.hellokoding.auth.model.Tag;
import com.hellokoding.auth.model.User;
import com.hellokoding.auth.repository.TagRepository;
import com.hellokoding.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class TagController {

    @Autowired
    private TagRepository tagRepository;



    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/tag", method = RequestMethod.POST, consumes = "application/json")
    public void login(@RequestBody Tag tag) throws IllegalArgumentException {

        tag.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
         tagRepository.save(tag);

    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/tag", method = RequestMethod.GET)
    public List<Tag> get() throws IllegalArgumentException {


        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Tag> tags = tagRepository.findAllByUserIs(user);
        for (Tag tag : tags) {
            tag.setUser(null);
        }

        return tags;

    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/tag/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Integer id) throws IllegalArgumentException {

        Tag data = tagRepository.findOne(id);
        tagRepository.delete(data);

    }

    @ExceptionHandler
    void handleIllegalArgumentException(Exception e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), "Failed");
    }
}
