package com.example.demo.Repository;

import com.example.demo.Beans.CropNegotiationRequest;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropNegotiationRepository extends JpaRepository<CropNegotiationRequest,Long> {



    CropNegotiationRequest findCropNegotiationRequestsByBuyerId(Long buyerId);




    Page<CropNegotiationRequest> findCropNegotiationsByCropDetailsId(Long cropDetailsId, Pageable pageable);

    CropNegotiationRequest findCropNegotiationRequestByCropDetailsIdAndBuyerId(Long id, Long id1);
}
