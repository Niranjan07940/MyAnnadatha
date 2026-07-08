package com.example.demo.Service;

import com.example.demo.Beans.CropDetails;
import com.example.demo.Beans.DeletedCropDetails;
import com.example.demo.Beans.Orders;
import com.example.demo.Enum.OrderStatus;
import com.example.demo.Repository.CropDetailsRepository;
import com.example.demo.Repository.DeletedCropDetailsRepository;
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

    @Autowired
    private DeletedCropDetailsRepository deletedCropDetailsRepository;



    @Transactional
    public Orders placeOrder(Orders order) throws Exception{

        order.setOrderStatus(OrderStatus.ORDERED);
        Orders placeOrder=ordersRepository.save(order);

        CropDetails cropDetails=cropDetailsRepository.findCropDetailsById(order.getCropDetails().getId());

        DeletedCropDetails deletedCropDetails= new DeletedCropDetails();
        deletedCropDetails.setCropQuantity(cropDetails.getCropQuantity());
        deletedCropDetails.setCropPrice(cropDetails.getCropPrice());
        deletedCropDetails.setImageUrls(cropDetails.getImageUrls());
        deletedCropDetails.setCropLocation(cropDetails.getCropLocation());
        deletedCropDetails.setUser(cropDetails.getUser());
        deletedCropDetails.setSubCategory(cropDetails.getSubCategory());
        deletedCropDetailsRepository.save(deletedCropDetails);

        cropDetailsRepository.delete(cropDetails);

        return placeOrder;
    }
}
