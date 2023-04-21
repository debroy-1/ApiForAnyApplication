package com.example.api.ApiApplication.UserResourcePackage;


import java.net.URI;
import java.util.List;

import com.example.api.ApiApplication.DAO.UserDAOService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.api.ApiApplication.user.User;

import jakarta.validation.Valid;

@RestController
public class  UserResource {
    private UserDAOService service;
public UserResource(UserDAOService service){
    this.service=service;
}



    @GetMapping("/users")
    public List<User> getAllUser() {
        return   service.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
         User user =   service.findOne(id);
        if (user==null)
            throw new UserIdNotFound("id=" + id);

        return  user;
    }

    @PostMapping("/userAdd")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User saved =  service.save(user);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(saved.getId()).
                toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteId(@PathVariable int id) {
         service.deleteById(id);

    }
}
