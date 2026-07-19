package com.example.demo.DTO;

import com.example.demo.Beans.CropNegotiationAccepted;
import com.example.demo.Beans.DeliveryAddress;
import lombok.Data;

@Data
public class CropOrdered {

    private CropNegotiationAccepted cropNegotiationAccepted;

    private Long deliveryAddressId;
}
