package com.example.BillSwift.Controller;


import com.example.BillSwift.Service.UserService;
import com.example.BillSwift.io.UserRequest;
import com.example.BillSwift.io.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody UserRequest userRequest) {
      try{
          return userService.createUser(userRequest);
      }catch(Exception e){
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
      }
    }

    @GetMapping("/users")
    public List<UserResponse> readUsers () {
          return userService.readUsers();
    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {
         try{
              userService.deleteUser(id);
         }catch(Exception e){
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
         }
    }

}
