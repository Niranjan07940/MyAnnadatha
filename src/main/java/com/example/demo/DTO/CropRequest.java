package com.example.demo.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CropRequest {

    private Long itemId;

    private Double cropPrice;
    private Double cropQuantity;

    private Long userId;

}
