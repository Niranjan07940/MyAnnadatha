package com.example.demo.Repository;

import com.example.demo.Beans.CropNegotiationAccepted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropNegotiationAcceptedRepository extends JpaRepository<CropNegotiationAccepted,Long> {
    CropNegotiationAccepted findByCropDetailsId(Long cropDetailsId);
}
