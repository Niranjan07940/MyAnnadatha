package com.example.demo.Controller;

import com.example.demo.Beans.Orders;
import com.example.demo.Repository.OrdersRepository;
import com.example.demo.Service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping("/orders/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody Orders order) {
        Map<String,Object> map = new HashMap<>();
        try{
            Orders orders1 = ordersService.placeOrder(order);
            if(orders1 != null){
                return ResponseEntity.ok(orders1);
            }
            return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST);
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
