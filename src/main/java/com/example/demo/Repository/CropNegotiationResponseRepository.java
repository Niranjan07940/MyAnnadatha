package com.example.demo.Repository;

import com.example.demo.Beans.CropNegotiationResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropNegotiationResponseRepository extends JpaRepository<CropNegotiationResponse,Long> {


    CropNegotiationResponse findCropNegotiationResponseByCropNegotiationRequestId(Long cropNegotiationRequestId);

    CropNegotiationResponse findCropNegotiationResponseById(Long id);

    CropNegotiationResponse findByCropNegotiationRequestId(Long id);
}
