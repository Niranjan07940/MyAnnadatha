package com.example.demo.Service;

import com.example.demo.Beans.CropDetails;
import com.example.demo.Beans.CropNegotiationAccepted;
import com.example.demo.Beans.Orders;
import com.example.demo.Enum.CropDetailsStatus;
import com.example.demo.Enum.OrderStatus;
import com.example.demo.Repository.CropDetailsRepository;
import com.example.demo.Repository.CropNegotiationAcceptedRepository;
import com.example.demo.Repository.OrdersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CropDetailsRepository cropDetailsRepository;

    @Autowired
    private CropNegotiationAcceptedRepository cropNegotiationAcceptedRepository;


    @Transactional
    public Orders placeOrder(Orders order){

        order.setOrderStatus(OrderStatus.ORDERED);

        CropDetails cropDetails=cropDetailsRepository.findCropDetailsById(order.getCropDetails().getId());
        cropDetails.setCropDetailsStatus(CropDetailsStatus.CROP_ACCEPTED);
        cropDetailsRepository.save(cropDetails);

        return ordersRepository.save(order);
    }

    public Page<Orders> getAllOrders(Long userId, int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));
        return ordersRepository.findAllOrdersByBuyerId(userId,pageable);
    }

    @Transactional
    public Orders deleteOrder(Long orderId) {
        Orders order = ordersRepository.findById(orderId).orElse(null);
        if((order != null ? order.getCropDetails() : null) !=null){
            CropDetails cropDetails = order.getCropDetails();
            cropDetails.setCropDetailsStatus(CropDetailsStatus.WAITING);
            cropDetailsRepository.save(cropDetails);
            CropNegotiationAccepted cropNegotiationAccepted=cropNegotiationAcceptedRepository.findByCropDetailsId(cropDetails.getId());
            if(cropNegotiationAccepted!=null){
                cropNegotiationAcceptedRepository.delete(cropNegotiationAccepted);
            }
        }
        return ordersRepository.deleteOrdersById(orderId);
    }
}
