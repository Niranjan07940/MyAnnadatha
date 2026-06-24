package com.example.demo.Service;


import com.example.demo.Beans.*;
import com.example.demo.DTO.CropRequest;
import com.example.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CropDetailsService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CropDetailsRepository cropDetailsRepository;


    public CropDetails uploadCrop(CropRequest cropRequest){
        CropDetails cropDetails= new CropDetails();
        cropDetails.setCropPrice(cropRequest.getCropPrice());
        cropDetails.setCropQuantity(cropRequest.getCropQuantity());
        cropDetails.setSubCategory(subCategoryRepository.findSubCategoryById(cropRequest.getItemId()));
        cropDetails.setUser(userRepository.findUserById(cropRequest.getUserId()));
        return cropDetailsRepository.save(cropDetails);

    }


}
