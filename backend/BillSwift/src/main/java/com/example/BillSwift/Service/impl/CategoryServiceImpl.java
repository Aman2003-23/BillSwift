package com.example.BillSwift.Service.impl;

import com.example.BillSwift.Entity.CategoryEntity;
import com.example.BillSwift.Repository.CategoryRepository;
import com.example.BillSwift.Service.CategoryService;
import com.example.BillSwift.io.CategoryRequest;
import com.example.BillSwift.io.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl  implements CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse add(CategoryRequest request) {
       CategoryEntity newCategory= convertToEntity(request);     // to convert request object into an entity object
       newCategory= categoryRepository.save(newCategory);
      return convertToResponse(newCategory);

    }

    private CategoryResponse convertToResponse(CategoryEntity newCategory) {
      return  CategoryResponse.builder()
                .categoryId(newCategory.getCategoryId())
                .name(newCategory.getName())
                .description(newCategory.getDescription())
                .bgColor(newCategory.getBgColor())
                .imgUrl(newCategory.getImgUrl())
                .createdAt(newCategory.getCreatedAt())
                .updatedAt(newCategory.getUpdatedAt())
                .build();
    }

    private CategoryEntity convertToEntity(CategoryRequest request) {
        //builder pattern
      return  CategoryEntity.builder()
                .categoryId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .bgColor(request.getBgColor())
                .build();
    }
}
