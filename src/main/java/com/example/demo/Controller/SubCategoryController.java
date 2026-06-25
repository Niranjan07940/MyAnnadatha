package com.example.demo.Controller;

import com.example.demo.Service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("/crop/getSubCategories")
    public ResponseEntity<?> getSubcategories(){
        return new ResponseEntity<>(subCategoryService.getSubCategory(), HttpStatusCode.valueOf(200));

    }
}
