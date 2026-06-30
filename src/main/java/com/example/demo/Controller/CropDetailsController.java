package com.example.demo.Controller;

import com.example.demo.Beans.CropDetails;
import com.example.demo.DTO.CropRequest;
import com.example.demo.DTO.NearbyCropResponse;
import com.example.demo.Service.CropDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
            if(cropDetails!=null)return new ResponseEntity<>(cropDetails, HttpStatusCode.valueOf(200));
        }catch(Exception e){
            map.put("message",e.getMessage());
        }return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }

    @GetMapping("/crop/getFreshCrops")
    public ResponseEntity<?> getFreshCropDetails(@RequestParam("page")int page){
        Map<String,Object> map = new HashMap<>();
        try{
            Page<CropDetails> cropDetails=cropDetailsService.getCrops(page,10);
            if(!cropDetails.isEmpty())return new ResponseEntity<>(cropDetails,HttpStatusCode.valueOf(200));
        }catch(Exception e){
            map.put("message",e.getMessage());
        }return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }

    @GetMapping("/crop/nearBy")
    public ResponseEntity<?> getNearByCrops(@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude, @RequestParam(defaultValue = "60") Double radius){
        Map<String,Object> map=new HashMap<>();
        try{
            List<NearbyCropResponse> list=cropDetailsService.getNearByCrops(latitude,longitude,radius);
            if(list.isEmpty()){
                System.out.println("testing ");
                map.put("message","No crops avaliable nearBy you!");
                return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
            }
        }
        catch(Exception e){
            map.put("message",e.getMessage());
            return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
        }
        return ResponseEntity.ok(cropDetailsService.getNearByCrops(latitude,longitude,radius));
    }


}
