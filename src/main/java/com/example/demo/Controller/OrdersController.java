package com.example.demo.Controller;

import com.example.demo.Beans.Orders;
import com.example.demo.Repository.OrdersRepository;
import com.example.demo.Service.OrdersService;
import com.example.demo.Utility.JwtUtility;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin
@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private JwtUtility jwtUtility;

    @GetMapping("/order/getYourOrders")
    public ResponseEntity<?> getOrders(HttpServletRequest request,@RequestParam("page") int page){
        Map<String,Object> map = new HashMap<>();
        Long userId=jwtUtility.getUserId(request.getHeader("Authorization").substring(7));
        try{
            Page<Orders> allOrders=ordersService.getAllOrders(userId,page);
            if(!allOrders.isEmpty()){
                return new  ResponseEntity<>(allOrders,HttpStatus.OK);
            }
            map.put("message","Not ordered anything yet");
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new  ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }

    @PostMapping("/order/deleteOrder")
    public ResponseEntity<?> deleteOrder(@RequestParam("orderId") Long  orderId){
        Map<String,Object> map = new HashMap<>();
        try{
            Orders deleteOrder=ordersService.deleteOrder(orderId);
            if(deleteOrder!=null){
                return new ResponseEntity<>(deleteOrder,HttpStatus.OK);
            }
            map.put("message","no order is there to delete");
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }
}
