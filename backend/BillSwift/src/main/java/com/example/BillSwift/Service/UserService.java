package com.example.BillSwift.Service;

import com.example.BillSwift.io.UserRequest;
import com.example.BillSwift.io.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);

    String getUserRole(String email);

    List<UserResponse> readUsers();

    void deleteUser(String id);
}
