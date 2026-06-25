package com.example.demo.Controller;

import com.example.demo.Beans.User;
import com.example.demo.Service.UserService;
import com.example.demo.Utility.JwtUtility;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtility jwtUtility;



    @PostMapping("/user/updateWithProfile")
    public ResponseEntity<?> updateProfile(@ModelAttribute User user, @RequestPart("file") MultipartFile file) throws Exception{
        user.setProfileUrl(userService.uploadImage(file));
        User savedUser=userService.register(user);
        Map<String,Object> map=new HashMap<>();
        try{
            if(savedUser!=null){
                return new ResponseEntity<>(savedUser, HttpStatus.OK);
            }
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(400));
    }

    @PostMapping("/user/updateProfile")
    public ResponseEntity<?> updateProfile(@RequestBody User user){
        Map<String,Object> map= new HashMap<>();
        try{
            User updateUser=userService.updateUser(user);
            return new ResponseEntity<>(updateUser,HttpStatusCode.valueOf(200));
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }

    @PostMapping("/user/getUser")
    public ResponseEntity<?> getProfile(@RequestParam("id") Long id){
        Map<String, Object> map = new HashMap<>();
        Optional<?> user=userService.getUser(id);
        if(user.isPresent()){
            return new ResponseEntity<>(user,HttpStatusCode.valueOf(200));
        }
        map.put("message", "user not found");
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }

    @GetMapping("/greetMessage")
    public ResponseEntity<?> greetMessage(HttpServletRequest request,HttpServletResponse response){
        Long userId=jwtUtility.getUserId(request.getHeader("Authorization").substring(7));
        User user=userService.getUserDetails(userId);
        Map<String,Object> map = new HashMap<>();
        LocalTime currentTime=LocalTime.now();
        System.out.println(currentTime);
        if(currentTime.isBefore(LocalTime.NOON)){
            map.put("message","Good Morning, "+user.getUsername());
            return new ResponseEntity<>(map,HttpStatusCode.valueOf(200));
        }
        else if(currentTime.isBefore(LocalTime.of(17,0))){
            map.put("message","Good AfterNoon, "+user.getUsername());
            return new ResponseEntity<>(map,HttpStatusCode.valueOf(200));
        }
        else if(currentTime.isBefore(LocalTime.of(21,0))){
            map.put("message","Good Evening, "+user.getUsername());
            return new ResponseEntity<>(map,HttpStatusCode.valueOf(200));
        }
        map.put("message","Good Night, "+user.getUsername());
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(200));
    }

}
