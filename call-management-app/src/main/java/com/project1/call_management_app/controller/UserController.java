package com.project1.call_management_app.controller;

import com.project1.call_management_app.dto.UserDTO;
import com.project1.call_management_app.model.AuthenticationRequest;
import com.project1.call_management_app.model.AuthenticationResponse;
import com.project1.call_management_app.model.RegisterRequest;
import com.project1.call_management_app.model.User;
import com.project1.call_management_app.service.UserService;
import com.project1.call_management_app.service.impl.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody RegisterRequest request) {
       return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody AuthenticationRequest request) {
       return ResponseEntity.ok(authenticationService.authenticate(request));
    }

//    @PostMapping("/register")
//    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
//        UserDTO createdUser = userService.createUser(user);
//        return new ResponseEntity(HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<UserDTO> getUserByPhoneNumber(@PathVariable String phone) {
        return userService.getUserByPhoneNumber(phone)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

