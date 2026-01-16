package com.example.BillSwift.Service.impl;

import com.example.BillSwift.Entity.CategoryEntity;
import com.example.BillSwift.Repository.CategoryRepository;
import com.example.BillSwift.Service.CategoryService;
import com.example.BillSwift.Service.FileUploadService;
import com.example.BillSwift.io.CategoryRequest;
import com.example.BillSwift.io.CategoryResponse;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl  implements CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;
    private final FileUploadService fileUploadService;

    @Override
    public CategoryResponse add(CategoryRequest request, MultipartFile file) throws IOException {
      String fileName=  UUID.randomUUID().toString()+"."+ StringUtils.getFilenameExtension(file.getOriginalFilename());
     Path uploadPath=  Paths.get("uploads").toAbsolutePath().normalize();
     Files.createDirectories(uploadPath);
     Path targetLocation=uploadPath.resolve(fileName);
     Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
     String imgUrl = "http://localhost:8080/api/v1.0/uploads/"+fileName;
       CategoryEntity newCategory= convertToEntity(request);     // to convert request object into an entity object
        newCategory.setImgUrl(imgUrl);
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

    @Override
    public List<CategoryResponse> read() {
       return categoryRepository.findAll()
                .stream()
                .map(categoryEntity -> convertToResponse(categoryEntity))
                .collect(Collectors.toList());
    }

    @Override
    public  void delete(String categoryId) {
      CategoryEntity existingCategory= categoryRepository.findByCategoryId(categoryId)   //returns an optinal categoryEntity
              .orElseThrow(()-> new RuntimeException("Category not found"));
      categoryRepository.delete(existingCategory);
    }
}
