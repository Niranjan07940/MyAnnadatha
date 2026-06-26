package com.example.demo.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    private static final String OTP_PREFIX = "OTP_";
    private static final long EXPIRE_MINUTES = 5 ;

    @Value("${twilio.whatsapp-number}")
    private String twilioPhoneNumber;
    @Value("${twilio.account.sid}")
    private String accountSid;
    @Value("${twilio.auth.token}")
    private String authToken;
    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    public String generateOtp(String identifier) {
        String otpStr = String.valueOf(100000 + new SecureRandom().nextInt(899999));
        redisTemplate.opsForValue().set(OTP_PREFIX + identifier, otpStr, EXPIRE_MINUTES, TimeUnit.MINUTES);
        return otpStr;
    }

    public boolean validateOtp(String identifier, String userOtp) {
        String cachedOtp = redisTemplate.opsForValue().get(OTP_PREFIX + identifier);
        if (cachedOtp != null && cachedOtp.equals(userOtp)) {
            redisTemplate.delete(OTP_PREFIX + identifier);
            return true;
        }return false;
    }

    public void sendOtp(String phoneNumber, String otp) {
        String to = "whatsapp:+91" + phoneNumber;
        String messageBody = "Your login OTP for MyAnnadhatha is "+ otp;
        Message message = Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(twilioPhoneNumber),
                messageBody
        ).create();
    }
}