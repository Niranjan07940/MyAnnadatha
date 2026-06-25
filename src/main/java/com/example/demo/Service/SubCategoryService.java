package com.example.demo.Service;

import com.example.demo.Beans.SubCategory;
import com.example.demo.Repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;
    public List<SubCategory> getSubCategory() {
        return subCategoryRepository.findAll();
    }
}
