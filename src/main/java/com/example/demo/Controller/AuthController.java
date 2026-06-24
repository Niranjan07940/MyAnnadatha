package com.example.demo.Controller;



import com.example.demo.Beans.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.OtpService;
import com.example.demo.Service.UserService;
import com.example.demo.Utility.JwtUtility;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private OtpService otpService;
    @Autowired 
    private UserRepository userRepository;

    @PostMapping("/auth/signup")
    public ResponseEntity<?> registerUser(@ModelAttribute User user, @RequestPart("file")MultipartFile file) throws Exception {
        user.setProfileUrl(userService.uploadImage(file));
        User savedUser=userService.register(user);
        Map<String,Object> map=new HashMap<>();
        try{
            if(savedUser!=null){
                return new ResponseEntity<>(savedUser,HttpStatus.OK);
            }
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        String message="";
        Map<String,Object> map = new HashMap<>();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
            message=jwtUtility.generateToken(user.getUsername());
            map.put("message",message);
            System.out.println(message);
            return new  ResponseEntity<>(map, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            message=e.getMessage();
            map.put("message",message);

            return new  ResponseEntity<>(map, HttpStatusCode.valueOf(404));
        }
    }
    @GetMapping("/getMessage")
    public String test(){
        return "This is sample";
    }
    
        @GetMapping("/oauth/login")
        public void login(HttpServletResponse response) throws IOException {
            response.sendRedirect("/oauth2/authorization/google");
        }

    @PostMapping("auth/otpSignIn")
    public ResponseEntity<?> otpSignIn(@RequestBody User user){
        String message="";
        Map<String,Object> map = new HashMap<>();
        try{
            String otp = otpService.generateOtp(user.getPhoneNumber().toString());
            otpService.sendOtp(user.getPhoneNumber().toString(), otp);
            message="OTP sent successfully";
        }
        catch(Exception e){
            message=e.getMessage();
        }
        map.put("message",message);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @PostMapping("auth/ValidateOtp")
    public ResponseEntity<?> ValidateOtp(@RequestBody Map<String, Object> payload){
        String message="";
        Map<String,Object> map = new HashMap<>();
        String otp = (String) payload.get("otp");
        String phoneNumber = (String) payload.get("phoneNumber");
        User user = userRepository.findByPhoneNumber(phoneNumber);
        boolean isValid=false;
        if(user!=null){
            isValid = otpService.validateOtp(phoneNumber, otp);
        }
        if(isValid){
            message = jwtUtility.generateToken(user.getUsername());
            map.put("message",message);
            System.out.println(message);
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
        map.put("message","Invalid Otp");
        return  new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("auth/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody User user){
        String message="";
        Map<String,Object> map = new HashMap<>();

        User existingUser=userRepository.findByPhoneNumber(user.getPhoneNumber());
        if(existingUser==null){
            message="user does not exist,register first";
            map.put("message",message);
            return new ResponseEntity<>(map,HttpStatusCode.valueOf(404));
        }
        existingUser.setPassword(user.getPassword());
        userService.resetPassword(existingUser);
        message="password reset successful";
        map.put("message",message);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }


}
