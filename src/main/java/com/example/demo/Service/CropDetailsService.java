package com.example.demo.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.Beans.*;
import com.example.demo.DTO.CropRequest;
import com.example.demo.DTO.NearbyCropResponse;
import com.example.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class CropDetailsService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CropDetailsRepository cropDetailsRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private CropLocationRepository cropLocationRepository;



    public CropDetails uploadCrop(CropRequest cropRequest){
        CropLocation cropLocation= new CropLocation();
        cropLocation.setLatitude(cropRequest.getLatitude());
        cropLocation.setLongitude(cropRequest.getLongitude());
        cropLocationRepository.save(cropLocation);             // Crop Location Saved

        CropDetails cropDetails= new CropDetails();
        cropDetails.setCropPrice(cropRequest.getCropPrice());
        cropDetails.setCropQuantity(cropRequest.getCropQuantity());
        cropDetails.setImageUrls(cropRequest.getImages());
        cropDetails.setSubCategory(subCategoryRepository.findSubCategoryById(cropRequest.getItemId()));
        cropDetails.setUser(userRepository.findUserById(cropRequest.getUserId()));
        cropDetails.setCropLocation(cropLocation);
        return cropDetailsRepository.save(cropDetails);
    }

    public String uploadImage(MultipartFile file) throws Exception{
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "resource_type", "auto"
        ));
        return uploadResult.get("secure_url").toString();
    }

    public Page<CropDetails> getCrops(int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.DESC, "id")
        );
        return cropDetailsRepository.findAll(pageable);
    }

    public List<NearbyCropResponse> getNearByCrops(Double latitude, Double longitude, Double radius) {
        List<Object[]> result = cropDetailsRepository.findNearbyCrops(latitude, longitude, radius);
        return result.stream().map(row -> new NearbyCropResponse(
                (CropDetails) row[0], ((Number) row[1]).doubleValue()))
                .toList();
    }



}
