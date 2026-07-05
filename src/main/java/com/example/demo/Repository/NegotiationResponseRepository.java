package com.example.demo.Repository;

import com.example.demo.Beans.NegotiationRequest;
import com.example.demo.Beans.NegotiationResponse;
import com.example.demo.Beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NegotiationResponseRepository extends JpaRepository<NegotiationResponse,Long> {
    NegotiationResponse findNegotiationResponseByNegotiationRequest_Id(Long id);


    NegotiationResponse findNegotiationResponseByNegotiationRequestAndBuyerAndFarmer(NegotiationRequest negotiationRequest, User buyer, User farmer);
}
