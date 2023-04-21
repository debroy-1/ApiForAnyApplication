package com.example.api.ApiApplication.UserResourcePackage;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.example.api.ApiApplication.DAO.PostRepository;
import com.example.api.ApiApplication.DAO.UserRepository;
import com.example.api.ApiApplication.user.Post;
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
public class  UserJPAresource {
    private UserRepository repository;
    private PostRepository postRepository;


    public UserJPAresource( UserRepository repository,PostRepository postRepository) {

        this.repository=repository;
        this.postRepository=postRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> getAllUser() {
        return  repository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public Optional<User> getUserById(@PathVariable int id) {
        Optional<User> user =  repository.findById(id);
        if (user.isEmpty())
            throw new UserIdNotFound("id=" + id);

        return user;
    }

    @PostMapping("/jpa/userAdd")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User saved =  repository.save(user);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(saved.getId()).
                toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteId(@PathVariable int id) {
        repository.deleteById(id);

    }
    @GetMapping ("/jpa/users/{id}/posts")
    public List<Post>   getPostOfUserById(@PathVariable int id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty())
            throw new UserIdNotFound("id=" + id);
        return user.get().getPosts();

    }
    @PostMapping ("/jpa/users/{id}/makePost")
    public  List<Post> makePOSTUserById(@PathVariable int id,@Valid @RequestBody Post post) {
        Optional<User> user =  repository.findById(id);
        if (user.isEmpty())
            throw new UserIdNotFound("id=" + id);
post.setUser(user.get());
Post postMade=postRepository.save(post);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(postMade.getId()).
                toUri();
        return (List<Post>) ResponseEntity.created(location).build();

    }
}
