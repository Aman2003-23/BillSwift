package com.example.BillSwift.Service.impl;

import com.example.BillSwift.Entity.UserEntity;
import com.example.BillSwift.Repository.UserRepository;
import com.example.BillSwift.Service.UserService;
import com.example.BillSwift.io.UserRequest;
import com.example.BillSwift.io.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserResponse createUser(UserRequest request) {
        UserEntity newUser= convertToEntity(request);
        userRepository.save(newUser);
        return convertToResponse(newUser);

    }

    private UserResponse convertToResponse(UserEntity newUser) {
       return  UserResponse.builder()
                 .name(newUser.getName())
                 .email(newUser.getEmail())
                 .userId(newUser.getUserId())
                 .createdAt(newUser.getCreatedAt())
                 .updatedAt(newUser.getUpdatedAt())
                 .role(newUser.getRole())
                 .build();
    }

    private UserEntity convertToEntity(UserRequest request) {
       return UserEntity.builder()
                .userId(UUID.randomUUID().toString())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
               .role(request.getRole().toUpperCase())
               .name(request.getName())
                .build();
    }

    @Override
    public String getUserRole(String email) {
       UserEntity existingUser=  userRepository.findByEmail(email)
        .orElseThrow(()->new UsernameNotFoundException("uSER NOT FOUND FOR THE EMAIL: " + email));
       return existingUser.getRole();
    }

    @Override
    public List<UserResponse> readUsers() {
      return userRepository.findAll()
               .stream()
               .map(user->convertToResponse(user))
               .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(String id) {
   UserEntity existingUser= userRepository.findByUserId(id)
            .orElseThrow(()->new UsernameNotFoundException("uSER NOT FOUND FOR THE ID: " + id));
    userRepository.delete(existingUser);
    }
}
