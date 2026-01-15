package com.example.BillSwift.Controller;


import com.example.BillSwift.io.CategoryRequest;
import com.example.BillSwift.io.CategoryResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    public CategoryResponse addCategory(@RequestBody CategoryRequest request){
return null;
    }
}
