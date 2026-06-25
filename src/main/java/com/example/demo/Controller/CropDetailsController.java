package com.example.demo.Controller;

import com.example.demo.Beans.CropDetails;
import com.example.demo.DTO.CropRequest;
import com.example.demo.Service.CropDetailsService;
import jakarta.mail.Multipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class CropDetailsController {
    @Autowired
    private CropDetailsService cropDetailsService;

    @PostMapping("/crop/addCrop")
    public ResponseEntity<?> uploadCropDetails(@ModelAttribute("request") CropRequest cropRequest, @RequestPart("files")List<MultipartFile> multipartFiles) throws Exception {
        Map<String,Object> map = new HashMap<>();
        List<String> listImages=new ArrayList<>();
        for(MultipartFile file:multipartFiles){
          listImages.add(cropDetailsService.uploadImage(file));
        }
        cropRequest.setImages(listImages);
        try{
            CropDetails cropDetails=cropDetailsService.uploadCrop(cropRequest);
            if(cropDetails!=null){
                return new ResponseEntity<>(cropDetails, HttpStatusCode.valueOf(200));
            }
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }

}
