package com.example.demo.Controller;

import com.example.demo.Service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("/crop/getSubCategories")
    public ResponseEntity<?> getSubcategories(@RequestParam("catId") Long catId){
        return new ResponseEntity<>(subCategoryService.getSubCategory(catId), HttpStatusCode.valueOf(200));
    }
}
