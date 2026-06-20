package com.example.demo.Service;

import com.example.demo.Beans.User;
import com.example.demo.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User register(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return userRepository.save(user);
    }

    public User resetPassword(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return userRepository.save(user);
    }
}
