package com.project1.call_management_app.service;

import com.project1.call_management_app.dto.UserDTO;
import com.project1.call_management_app.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO createUser(User user);
    Optional<UserDTO> getUserById(Long id);
    Optional<UserDTO> getUserByEmail(String email);
    Optional<UserDTO> getUserByPhoneNumber(String phoneNumber);
    List<UserDTO> getAllUsers();
    void deleteUser(Long id);
}

