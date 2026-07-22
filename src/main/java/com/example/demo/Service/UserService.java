package com.example.demo.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.Beans.*;
import com.example.demo.DTO.UpdatePassword;
import com.example.demo.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
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

    @Autowired
    private RatingsRepository ratingsRepository;

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Autowired
    private DeliveryAddressRepository deliveryAddressRepository;


    public User register(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        User user=userRepository.findUserById(favourites.getFarmer().getId());
        if(user!=null){
            user.setFavouriteCount(user.getFavouriteCount()+1);
            userRepository.save(user);
        }
        return favouritesRepository.save(favourites);

    }

    public Page<Favourites> getAllLikedUsers(Long farmerId, int page, int pageSize) {
                Pageable pageable = PageRequest.of(page, pageSize);
                return favouritesRepository.findByFarmer_Id(farmerId,pageable);
    }


    @Transactional
    public void deleteLike(Long buyerId, Long farmerId) {
        User user=userRepository.findUserById(farmerId);
        if(user!=null){
            user.setFavouriteCount(user.getFavouriteCount()-1);
            userRepository.save(user);
        }
        int count=favouritesRepository.deleteByBuyerIdAndFarmerId(buyerId, farmerId);
    }


    public ResponseEntity<?> updatePassword(UpdatePassword updatePassword) {
        Map<String, Object> map = new HashMap<>();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        User user = userRepository.findByEmail(username);
        if(user==null){
            map.put("message","user not found");
            return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
        }
        else if(!passwordEncoder.matches(updatePassword.getOldPassword(),user.getPassword())){
            map.put("message", "Old password is incorrect");
            return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
        }
        else if(!updatePassword.getNewPassword().equals(updatePassword.getConfirmPassword())){
            map.put("message","passwords do not match");
            return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
        }
        user.setPassword(passwordEncoder.encode(updatePassword.getNewPassword()));
        userRepository.save(user);
        map.put("message","Password Updated Successfully");
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(200));
    }

    public User getUserByEmail(String username) {
        return userRepository.findUserByEmail(username);
    }

    public Ratings addRating(Ratings ratings) {
        Ratings oldRating=ratingsRepository.findByBuyerIdAndFarmerId(ratings.getBuyer().getId(),ratings.getFarmer().getId());
        if(oldRating!=null){
            User user=userRepository.findUserById(oldRating.getFarmer().getId());
            if(user!=null){
                user.setTotalRating(user.getTotalRating()+((ratings.getRating()-oldRating.getRating())/user.getCount()));
                userRepository.save(user);
            }
            oldRating.setRating(ratings.getRating());
            return ratingsRepository.save(oldRating);
        }
        Ratings ratings1=ratingsRepository.save(ratings);
        User user=userRepository.findUserById(ratings.getFarmer().getId());
        user.setTotalRating(user.getTotalRating()+((ratings1.getRating()-user.getTotalRating())/
                (user.getCount()+1)));
        user.setCount(user.getCount()+1);
        userRepository.save(user);
        return ratings1;
    }

    public List<Ratings> getRatingsForUser(Long farmerId) {
        return ratingsRepository.findAllByFarmerId(farmerId);
    }

    public Reviews uploadReview(Reviews reviews) {
        return reviewsRepository.save(reviews);
    }

    public Page<Reviews> getReviews(Long farmerId,int page) {
        Pageable pageable = PageRequest.of(page, 3,Sort.by(Sort.Direction.DESC,"id"));
        return reviewsRepository.findAllByFarmerId(farmerId,pageable);
    }

    public boolean getLike(Long buyerId,Long farmerId) {
        return favouritesRepository.existsByBuyerIdAndFarmerId(buyerId,farmerId);
    }

    public Integer getCount(Long farmerId) {
        User user=userRepository.findUserById(farmerId);
        return user.getFavouriteCount();
    }

    public List<DeliveryAddress> getAddresses(Long userId) {
        return deliveryAddressRepository.findDeliveryAddressByUserId(userId);
    }

    public DeliveryAddress addAddress(DeliveryAddress deliveryAddress) {
        return deliveryAddressRepository.save(deliveryAddress);
    }

    @Transactional
    public DeliveryAddress deleteAddress(Long addressId) {
        return deliveryAddressRepository.deleteDeliveryAddressById(addressId);
    }
}
