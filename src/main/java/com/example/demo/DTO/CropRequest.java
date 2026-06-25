package com.example.demo.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CropRequest {

    private Long itemId;

    private Double cropPrice;
    private Double cropQuantity;

    private List<String> images;



    private Long userId;

}
