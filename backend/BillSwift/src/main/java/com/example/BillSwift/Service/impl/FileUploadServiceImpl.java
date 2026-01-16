package com.example.BillSwift.Service.impl;

import com.example.BillSwift.Entity.CategoryEntity;
import com.example.BillSwift.Repository.CategoryRepository;
import com.example.BillSwift.Service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final CategoryRepository categoryRepository;

    @Override
    public String uploadFile(MultipartFile file) {
       String filenameExtension =  file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
       String key = UUID.randomUUID().toString() + "." + filenameExtension;
       try{
           CategoryEntity categoryEntity = CategoryEntity.builder()
                   .imgUrl(key)
                   .build();
          categoryRepository.save(categoryEntity);
       }catch (Exception e){
           throw new RuntimeException("error adding imgUrl", e);
       }
       return "saved";
    }

    @Override
    public boolean delete(String imgUrl) {
       String filename=imgUrl.substring(imgUrl.lastIndexOf(".")+1);
       CategoryEntity categoryEntity=CategoryEntity.builder()
               .imgUrl(filename)
               .build();

       categoryRepository.delete(categoryEntity);
       return true;
    }
}
