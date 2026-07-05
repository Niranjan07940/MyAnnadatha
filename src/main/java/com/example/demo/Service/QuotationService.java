package com.example.demo.Service;

import com.example.demo.Beans.*;
import com.example.demo.Enum.NegotiationStatus;
import com.example.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



@Service
public class QuotationService {
    @Autowired
    private QuotationRepository quotationRepository;

    @Autowired
    private NegotiationRepository negotiationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AcceptedQuotationsRepository acceptedQuotationsRepository;

    @Autowired
    private NegotiationResponseRepository negotiationResponseRepository;


    public RequestQuotation uploadQuotation(RequestQuotation requestQuotation,Long userId) {
        User user=userRepository.findUserById(userId);
        requestQuotation.setUser(user);
        return quotationRepository.save(requestQuotation);
    }

    public Page<RequestQuotation> getQutotaions(Long userId,int page) {
        Pageable pageable=PageRequest.of(page,10,Sort.Direction.DESC,"id");
        return quotationRepository.findByUserIdNotAndNegotiationStatusNot(userId, NegotiationStatus.ACCEPTED,pageable);
    }


    public Page<RequestQuotation> getYourQuotations(Long userId,int page) {
        Pageable pageable=PageRequest.of(page,10,Sort.Direction.DESC, "id");
        return quotationRepository.findRequestQuotationByUserId(userId,pageable);
    }

    public NegotiationRequest createNegotiation(Long qid,Long userId,Double cropPrice) {
        NegotiationRequest negotiationRequest1 = negotiationRepository.getNegotiationRequestByRequestQuotationIdAndUserId(qid,userId);
        if(negotiationRequest1!=null){
            NegotiationResponse negotiationResponse = negotiationResponseRepository
                    .findNegotiationResponseByNegotiationRequestAndBuyerAndFarmer(negotiationRequest1,
                            negotiationRequest1.getRequestQuotation().getUser(),
                            negotiationRequest1.getUser());
            if(negotiationResponse!=null){
                negotiationResponseRepository.delete(negotiationResponse);
            }
            negotiationRepository.delete(negotiationRequest1);
        }

        User user=userRepository.findUserById(userId);
        RequestQuotation requestQuotation=quotationRepository.findRequestQuotationById(qid);
        requestQuotation.setNegotiationStatus(NegotiationStatus.NEGOTIATING);
        quotationRepository.save(requestQuotation);

        NegotiationRequest negotiationRequest= new NegotiationRequest();
        negotiationRequest.setUser(user);
        negotiationRequest.setRequestQuotation(requestQuotation);
        negotiationRequest.setCropPrice(cropPrice);
        return negotiationRepository.save(negotiationRequest);
    }

    public RequestQuotation getQuote(Long qid) {
        return quotationRepository.findRequestQuotationById(qid);
    }

    public AcceptedQuotations acceptQuotation(AcceptedQuotations acceptedQuotations) {
        RequestQuotation requestQuotation=quotationRepository.findRequestQuotationById(acceptedQuotations.getRequestQuotation().getId());
        requestQuotation.setNegotiationStatus(NegotiationStatus.ACCEPTED);
        requestQuotation.setCropPrice(acceptedQuotations.getAcceptedPrice());
        quotationRepository.save(requestQuotation);
        User user=userRepository.findUserById(acceptedQuotations.getUser().getId());
        AcceptedQuotations ac=new AcceptedQuotations();
        ac.setRequestQuotation(requestQuotation);
        ac.setUser(user);
        return acceptedQuotationsRepository.save(ac);

    }

    public Page<AcceptedQuotations> getYourAcceptedQuotations(Long userId, int page) {
        Pageable pageable=PageRequest.of(page,10,Sort.Direction.DESC, "id");
        return acceptedQuotationsRepository.findByUserId(userId,pageable);
    }

    public Page<NegotiationRequest> getNegotiations(Long qid,int page) {
        Pageable pageable=PageRequest.of(page,10,Sort.Direction.DESC,"id");
        return negotiationRepository.findNegotiationRequestsByRequestQuotation_Id(qid,pageable);
    }


    public NegotiationRequest getNegotiationRequest(Long userId, Long qid) {
        return negotiationRepository.getByRequestQuotation_IdAndUser_Id(qid, userId);
    }

    public NegotiationResponse createNegotiationResponse(NegotiationResponse negotiationResponse) {
        NegotiationResponse negotiationResponse1=negotiationResponseRepository.findNegotiationResponseByNegotiationRequestAndBuyerAndFarmer(negotiationResponse.getNegotiationRequest(),
                negotiationResponse.getBuyer(),negotiationResponse.getFarmer());
        if(negotiationResponse1!=null){
            negotiationResponseRepository.delete(negotiationResponse1);
        }
        NegotiationRequest negotiationRequest=negotiationRepository.getNegotiationRequestById(negotiationResponse.getNegotiationRequest().getId());
        User user=userRepository.findUserById(negotiationResponse.getFarmer().getId());
        User buyer=userRepository.findUserById(negotiationResponse.getBuyer().getId());

        negotiationResponse.setNegotiationRequest(negotiationRequest);
        negotiationResponse.setFarmer(user);
        negotiationResponse.setBuyer(buyer);

        return negotiationResponseRepository.save(negotiationResponse);
    }

    public NegotiationResponse getReponse(Long Id) {
        return negotiationResponseRepository.findNegotiationResponseByNegotiationRequest_Id(Id);
    }
}
