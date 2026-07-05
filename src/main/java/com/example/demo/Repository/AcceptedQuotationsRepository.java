package com.example.demo.Repository;

import com.example.demo.Beans.AcceptedQuotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcceptedQuotationsRepository extends JpaRepository<AcceptedQuotations,Long> {
    Page<AcceptedQuotations> findByUserId(Long userId, Pageable pageable);
}
