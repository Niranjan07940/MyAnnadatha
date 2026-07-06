package com.example.demo.Repository;

import com.example.demo.Beans.Reviews;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews,Long> {
    Page<Reviews> findAllByFarmerId(Long farmerId, Pageable pageable);
}
