package com.example.demo.Service;

import com.example.demo.Beans.CropDetails;
import com.example.demo.Beans.CropNegotiationAccepted;
import com.example.demo.Beans.Orders;
import com.example.demo.Enum.CropDetailsStatus;
import com.example.demo.Enum.OrderStatus;
import com.example.demo.Repository.CropDetailsRepository;
import com.example.demo.Repository.CropNegotiationAcceptedRepository;
import com.example.demo.Repository.CropOrdersRepository;
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
    private CropOrdersRepository ordersRepository;

    @Autowired
    private CropDetailsRepository cropDetailsRepository;

    @Autowired
    private CropNegotiationAcceptedRepository cropNegotiationAcceptedRepository;


    public Page<Orders> getAllOrders(Long userId, int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));
        return ordersRepository.findAllOrdersByBuyerId(userId,pageable);
    }

    @Transactional
    public Orders cancleOrder(Long orderId) {
        //TODO RESTRICTION FOR ORDER DELETION IN TIME 10 HRS BEFORE DELIVERY
        Orders order = ordersRepository.findOrderById(orderId);
        CropDetails cropDetails=order.getCropDetails();
        if(cropDetails!=null){
            cropDetails.setCropDetailsStatus(CropDetailsStatus.WAITING);
            cropDetailsRepository.save(cropDetails);
            CropNegotiationAccepted cropNegotiationAccepted=cropNegotiationAcceptedRepository.findByCropDetailsId(cropDetails.getId());
            if(cropNegotiationAccepted!=null){
                cropNegotiationAcceptedRepository.delete(cropNegotiationAccepted);
            }
        }
        order.setOrderStatus(OrderStatus.CANCELLED);
        return ordersRepository.save(order);
    }
}
