package com.example.demo.Repository;

import com.example.demo.Beans.NegotiationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NegotiationRepository extends JpaRepository<NegotiationRequest,Long> {
    Page<NegotiationRequest> findByRequestQuotation_Id(Long qid, Pageable pageable);



    Page<NegotiationRequest> findNegotiationRequestsByRequestQuotation_Id(Long qid, Pageable pageable);



    boolean existsByRequestQuotation_IdAndUser_Id(Long qid, Long userId);

    NegotiationRequest getNegotiationRequestById(Long id);

    NegotiationRequest getByRequestQuotation_IdAndUser_Id(Long qid, Long userId);
}
