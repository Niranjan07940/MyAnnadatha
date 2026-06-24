package com.example.demo.Controller;

import com.example.demo.DTO.CropRequest;
import com.example.demo.Service.CropDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CropDetailsController {
    @Autowired
    private CropDetailsService cropDetailsService;

    @PostMapping("/crop/addCrop")
    public ResponseEntity<?> uploadCropDetails(@RequestBody CropRequest cropRequest) {

        return null;
    }
}
