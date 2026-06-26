package com.example.demo.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.Beans.Favourites;
import com.example.demo.Beans.User;
import com.example.demo.Repository.FavouritesRepository;
import com.example.demo.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private FavouritesRepository favouritesRepository;


    public User register(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return userRepository.save(user);
    }


    public User resetPassword(User user) {
        System.out.println(user.getPassword());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return userRepository.save(user);
    }

    public String uploadImage(MultipartFile file) throws Exception{
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "resource_type", "auto"
        ));
        return uploadResult.get("secure_url").toString();

    }

    public User updateUser(User user) {
        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return userRepository.save(user);
    }

    public Optional<?> getUser(Long id) {
        Optional<User> user=userRepository.findById(id);
        return user;

    }

    public User getUserDetails(Long userId) {
        return userRepository.findUserById(userId);
    }


    public Favourites uploadLikes(Favourites favourites) {
        return favouritesRepository.save(favourites);

    }


    public List<Favourites> getAllLikedUsers(Long farmerId) {
        return favouritesRepository.findByFarmer_Id(farmerId);
    }
}
