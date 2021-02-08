package com.library.controller;

import com.library.entity.User;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUserById (@PathVariable long id) {
        return new ResponseEntity<User>(userService.getUserById(id), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/api/users")
    public ResponseEntity<User> createNewUser (@Valid @RequestBody User user) {
        return new ResponseEntity<User>(userService.addNewUser(user), new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<User> editUserById (@PathVariable long id, @RequestBody User user) {
        return new ResponseEntity<User>(userService.editUserById(id, user), new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity deleteUserById(@PathVariable long id) {
        userService.deleteUserById(id);
        return new ResponseEntity(new HttpHeaders(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/api/users")
    public ResponseEntity<Page<User>> getAllUsers (@RequestParam(defaultValue = "0") int page) {
        return new ResponseEntity<Page<User>>(userService.getAllUsers(page), new HttpHeaders(), HttpStatus.OK);
    }

}
