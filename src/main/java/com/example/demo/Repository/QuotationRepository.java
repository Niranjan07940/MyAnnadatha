package com.example.demo.Repository;

import com.example.demo.Beans.RequestQuotation;
import com.example.demo.Enum.NegotiationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuotationRepository extends JpaRepository<RequestQuotation,Long> {
    Page<RequestQuotation> findRequestQuotationByUserId(Long userId, Pageable pageable);

    RequestQuotation findRequestQuotationById(Long qid);


    Page<RequestQuotation> findByUserIdNotAndNegotiationStatusNot(Long userId, NegotiationStatus negotiationStatus,Pageable pageable);
}
