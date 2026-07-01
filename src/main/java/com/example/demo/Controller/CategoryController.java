package com.example.demo.Controller;

import com.example.demo.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/getCategories")
    public ResponseEntity<?> getCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatusCode.valueOf(200));
    }

}
