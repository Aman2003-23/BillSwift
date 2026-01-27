package com.example.BillSwift.io;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String userId;

    private String email;
    private String name;
    private String password;

    private Timestamp updatedAt;
    private Timestamp createdAt;

    private String role;
}
