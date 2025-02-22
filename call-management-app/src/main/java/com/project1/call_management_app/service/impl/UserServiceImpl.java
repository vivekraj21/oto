package com.project1.call_management_app.service.impl;

import com.project1.call_management_app.dto.UserDTO;
import com.project1.call_management_app.model.User;
import com.project1.call_management_app.repository.UserRepository;
import com.project1.call_management_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getPhoneNumber(), user.getEmail());
    }

    private User convertToEntity(UserDTO userDTO) {
        return new User(null, userDTO.getName(), userDTO.getPhoneNumber(), userDTO.getEmail(), null);
    }

    @Override
    public UserDTO createUser(User user) {
        User userSaved = userRepository.save(user);
        return convertToDTO(userSaved);
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public Optional<UserDTO> getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(this::convertToDTO);
    }

    @Override
    public Optional<UserDTO> getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).map(this::convertToDTO);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

