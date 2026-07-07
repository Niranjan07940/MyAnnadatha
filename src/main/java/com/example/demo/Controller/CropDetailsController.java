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
        System.out.println("latitude: "+" "+latitude+" "+"longitude: "+" "+longitude);
        try{
            List<NearbyCropResponse> list=cropDetailsService.getNearByCrops(latitude,longitude,radius);
            if(list.isEmpty()){
                map.put("message","No crops available nearBy you!");
                return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
            }
        }catch(Exception e){
            map.put("message",e.getMessage());
            return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
        }return ResponseEntity.ok(cropDetailsService.getNearByCrops(latitude,longitude,radius));
    }

    @GetMapping("crop/getAllFarmars")
    public ResponseEntity<?> getAllFarmers(@RequestParam("subCategory") int subCategoryId,@RequestParam("page")int page){
        Map<String,Object> map = new HashMap<>();
        try{
            Page<CropDetails> farmers = cropDetailsService.getFarmersBySubCategory(subCategoryId, page);
            return new ResponseEntity<>(farmers,HttpStatusCode.valueOf(200));
        }catch(Exception e){
            map.put("message",e.getMessage());
            return  new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
        }
    }
    @GetMapping("/crop/getSubCategoryByCategoryId")
    public ResponseEntity<?> getCropDetailsByCategory(@RequestParam("catId") Long catId,@RequestParam("page") int page) {
        Map<String,Object> map= new HashMap<>();
        try{
            Page<CropDetails> crops = cropDetailsService.getCropDetailsByCategory(catId, page,10);
            if(!crops.isEmpty()){
                return new ResponseEntity<>(crops,HttpStatusCode.valueOf(200));
            }
        }
        catch(Exception e){
            map.put("message",e.getMessage());
            return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
        }
        map.put("message","no crops available for this category");
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }

    @GetMapping("/crop/getByFarmer")
    public ResponseEntity<?> getByFarmer(@RequestParam("farmerId") Long farmerId) {
        Map<String,Object> map= new HashMap<>();
        try{
            map.put("count",cropDetailsService.getCropDetailsCountByFarmer(farmerId));
            return new ResponseEntity<>(map,HttpStatusCode.valueOf(200));
        }catch(Exception e){
            map.put("message",e.getMessage());
            return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
        }
    }

    @GetMapping("/crop/getAllFarmerCrops")
    public ResponseEntity<?> getAllFarmerCrops(@RequestParam("farmerId") Long farmerId,@RequestParam("page") int page) {
        Map<String,Object> map= new HashMap<>();
        try{
            Page<CropDetails> cropDetails = cropDetailsService.getCropDetailsByFarmer(farmerId, page);
            if(!cropDetails.isEmpty()){
                return new ResponseEntity<>(cropDetails,HttpStatusCode.valueOf(200));
            }map.put("message","no crops available for this category");
            return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
        }catch(Exception e){
            map.put("message",e.getMessage());
            return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
        }
    }
    @GetMapping("/crop/getCropById")
    public ResponseEntity<?> getCropDetailsById(@RequestParam("cropDetailsId")Long cropDetailsId){
        Map<String,Object> map= new HashMap<>();
        try{
            CropDetails cropDetails=cropDetailsService.getCrop(cropDetailsId);
            if(cropDetails!=null){
                return new ResponseEntity<>(cropDetails,HttpStatusCode.valueOf(200));
            }
            map.put("message","no crop details for this id");
            return new ResponseEntity<>(map,HttpStatusCode.valueOf(200));
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }
    
}
