package com.example.demo.Service;

import com.example.demo.Beans.CropDetails;
import com.example.demo.Beans.Orders;
import com.example.demo.Enum.CropDetailsStatus;
import com.example.demo.Enum.OrderStatus;
import com.example.demo.Repository.CropDetailsRepository;
import com.example.demo.Repository.OrdersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CropDetailsRepository cropDetailsRepository;


    @Transactional
    public Orders placeOrder(Orders order){

        order.setOrderStatus(OrderStatus.ORDERED);

        CropDetails cropDetails=cropDetailsRepository.findCropDetailsById(order.getCropDetails().getId());
        cropDetails.setCropDetailsStatus(CropDetailsStatus.CROP_ACCEPTED);
        cropDetailsRepository.save(cropDetails);

        return ordersRepository.save(order);
    }
}
