package com.example.BillSwift.io;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;


@Builder
@Data
public class CategoryResponse {

    private String  categoryId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String imgUrl;
    private String name;
    private String description;

    private String bgColor;
}
