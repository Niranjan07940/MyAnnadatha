package com.example.demo.Repository;

import com.example.demo.Beans.Favourites;
import com.example.demo.Beans.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FavouritesRepository extends JpaRepository<Favourites,Long> {


    Page<Favourites> findByFarmer_Id(Long farmerId, Pageable pageable);


    @Modifying
    int deleteByBuyerIdAndFarmerId(Long buyerId, Long farmerId);



}
