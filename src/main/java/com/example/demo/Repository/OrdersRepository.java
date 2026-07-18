package com.example.demo.Repository;

import com.example.demo.Beans.Orders;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {
    Page<Orders> findAllOrdersByBuyerId(Long userId, Pageable pageable);
    @Transactional
    Orders deleteOrdersById(Long id);
}
