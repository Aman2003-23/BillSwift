package com.example.BillSwift.Service;

import org.springframework.web.multipart.MultipartFile;

public interface   FileUploadService {

    String uploadFile(MultipartFile file);

    boolean delete(String imgUrl);

}
