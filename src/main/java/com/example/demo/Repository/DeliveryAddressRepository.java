package com.example.demo.Repository;

import com.example.demo.Beans.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress,Long> {
    List<DeliveryAddress> findDeliveryAddressByUserId(Long id);

    DeliveryAddress deleteDeliveryAddressById(Long id);



    DeliveryAddress findDeliveryAddressesById(Long id);
}
