package com.hellokoding.auth.controller;

import com.hellokoding.auth.model.Data;
import com.hellokoding.auth.model.Tag;
import com.hellokoding.auth.model.User;
import com.hellokoding.auth.repository.DataRepository;
import com.hellokoding.auth.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class DataController {

    @Autowired
    private DataRepository dataRepository;
    @Autowired
    private TagRepository tagRepository;



    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/data", method = RequestMethod.POST, consumes = "application/json")
    public void save(@RequestBody Data data) throws IllegalArgumentException {

        if (data == null ||data.getTag() == null || data.getTag().getId()==null ){
            throw new IllegalArgumentException();
        }
         dataRepository.save(data);

    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public List<Data> get(@RequestParam(value = "search", required = false) String search) throws IllegalArgumentException {

       User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Data> data = dataRepository.findAllByTagIn(search != null ?
                tagRepository.findAllByUserIsAndIdentifierLike(user,"%"+search+"%")
                : tagRepository.findAllByUserIs(user));
        for (Data datum : data) {
            datum.getTag().setUser(null);
        }

        return data;

    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/data/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Integer id) throws IllegalArgumentException {

       Data data = dataRepository.findOne(id);
       dataRepository.delete(data);

    }


    @ExceptionHandler
    void handleIllegalArgumentException(Exception e, HttpServletResponse response) throws IOException {
        e.printStackTrace();
        response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), "Failed");
    }
}
