package com.example.BillSwift.Service;

import com.example.BillSwift.io.CategoryRequest;
import com.example.BillSwift.io.CategoryResponse;

public interface CategoryService {

    CategoryResponse add(CategoryRequest request);
}
