package com.example.demo.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CropRequest {
    private Long itemId;
    private Double cropPrice;
    private Double cropQuantity;
    private List<String> images;
    private Long userId;
}
