package com.example.demo.Repository;

import com.example.demo.Beans.Favourites;
import com.example.demo.Beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FavouritesRepository extends JpaRepository<Favourites,Long> {


    List<User> getBuyerByBuyerId(Long userId);

    List<Favourites> findByFarmer_Id(Long farmerId);
}
