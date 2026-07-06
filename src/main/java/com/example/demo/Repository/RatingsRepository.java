package com.example.demo.Repository;

import com.example.demo.Beans.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingsRepository extends JpaRepository<Ratings, Long> {

    List<Ratings> findAllByFarmerId(Long farmerId);
}
