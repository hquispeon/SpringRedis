package dev.hquispeon.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hquispeon.entity.User;
import dev.hquispeon.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService service;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @GetMapping
    public List<User> getAll() {
        return service.findAll();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return service.createUser(user);
    }
    
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUserById(id);
    }
}
