package com.example.demo.Service;


import com.example.demo.Beans.*;
import com.example.demo.Enum.CropDetailsStatus;
import com.example.demo.Enum.OrderStatus;
import com.example.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CropNegotiationService {

    @Autowired
    private CropNegotiationRepository  cropNegotiationRepository;

    @Autowired
    private CropNegotiationResponseRepository cropNegotiationResponseRepository;

    @Autowired
    private CropDetailsRepository cropDetailsRepository;

    @Autowired
    private CropNegotiationAcceptedRepository  cropNegotiationAcceptedRepository;


    @Autowired
    private CropOrdersRepository ordersRepository;


    public CropNegotiationRequest createRequest(CropNegotiationRequest cropNegotiationRequest) {
        CropNegotiationRequest existingCropNegotiationRequest=cropNegotiationRepository.findCropNegotiationRequestByCropDetailsIdAndBuyerId(cropNegotiationRequest.getCropDetails().getId(),cropNegotiationRequest.getBuyer().getId());
        if(existingCropNegotiationRequest!=null){
            CropNegotiationResponse cropNegotiationResponse=cropNegotiationResponseRepository.findByCropNegotiationRequestId(existingCropNegotiationRequest.getId());
            if(cropNegotiationResponse!=null){
                cropNegotiationResponseRepository.delete(cropNegotiationResponse);
            }
            cropNegotiationRepository.delete(existingCropNegotiationRequest);
        }
        CropDetails cropDetails=cropDetailsRepository.findCropDetailsById(cropNegotiationRequest.getCropDetails().getId());
        if(cropDetails!=null){
            cropDetails.setCropDetailsStatus(CropDetailsStatus.UNDER_NEGOTIATION);
            cropDetailsRepository.save(cropDetails);
        }
        return cropNegotiationRepository.save(cropNegotiationRequest);
    }

    public CropNegotiationResponse createNegotiationResponse(CropNegotiationResponse cropNegotiationResponse) {

       System.out.println("in crop negotiation response "+cropNegotiationResponse);
        return cropNegotiationResponseRepository.save(cropNegotiationResponse);
    }

    public CropNegotiationRequest getYourRequest(Long buyerId) {
        return cropNegotiationRepository.findCropNegotiationRequestsByBuyerId(buyerId);
    }

    public CropNegotiationResponse getNegotiationResponse(Long cropNegotiationRequestId) {
        return cropNegotiationResponseRepository.findCropNegotiationResponseByCropNegotiationRequestId(cropNegotiationRequestId);
    }

    public Page<CropNegotiationRequest> getNegotiations(Long cropDetailsId, int page) {
        Pageable pageable = PageRequest.of(page, 10,
                Sort.by(Sort.Direction.DESC, "id"));
        return cropNegotiationRepository.findCropNegotiationsByCropDetailsId(cropDetailsId,pageable);
    }

    public CropNegotiationAccepted acceptNegotiation(CropNegotiationAccepted cropNegotiationAccepted) {
        CropDetails cropDetails=cropDetailsRepository.findCropDetailsById(cropNegotiationAccepted.getCropDetails().getId());
        if(cropDetails!=null){
            cropDetails.setCropDetailsStatus(CropDetailsStatus.CROP_ACCEPTED);
            cropDetailsRepository.save(cropDetails);
        }
        CropNegotiationAccepted cropNegotiationAccepted1=cropNegotiationAcceptedRepository.save(cropNegotiationAccepted);
        Orders order= new Orders();
        order.setCropDetails(cropNegotiationAccepted.getCropDetails());
        order.setBuyer(cropNegotiationAccepted.getBuyer());
        order.setOrderStatus(OrderStatus.ORDERED);
        ordersRepository.save(order);
        return cropNegotiationAccepted1;
    }

    public CropNegotiationAccepted getAllAcceptedCropDetails(Long cropDetailsId) {
        return cropNegotiationAcceptedRepository.findByCropDetailsId(cropDetailsId);
    }
}
