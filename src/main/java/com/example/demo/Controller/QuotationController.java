package com.example.demo.Controller;

import com.example.demo.Beans.AcceptedQuotations;
import com.example.demo.Beans.NegotiationRequest;
import com.example.demo.Beans.NegotiationResponse;
import com.example.demo.Beans.RequestQuotation;
import com.example.demo.Service.QuotationService;
import com.example.demo.Utility.JwtUtility;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class QuotationController {

    @Autowired
    private QuotationService quotationService;

    @Autowired
    private JwtUtility jwtUtility;

    @PostMapping("/quote/addQuotation")
    public ResponseEntity<?> createQuote(@RequestBody RequestQuotation requestQuotation,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        Long userId=jwtUtility.getUserId(request.getHeader("Authorization").substring(7));
        try{
            RequestQuotation requestQuotation1=quotationService.uploadQuotation(requestQuotation,userId);
            if(requestQuotation1!=null){
                return new ResponseEntity<>(requestQuotation1, HttpStatusCode.valueOf(200));
            }
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }

    @GetMapping("/quote/getQuotations")
    public ResponseEntity<?> getAllQuotations(HttpServletRequest request,@RequestParam("page") int page){
        Map<String,Object> map= new HashMap<>();
        try{
            Page<RequestQuotation> list=quotationService.getQutotaions(jwtUtility.getUserId(request.getHeader("Authorization").substring(7)),page);
            if(!list.isEmpty()){
                return new ResponseEntity<>(list,HttpStatusCode.valueOf(200));
            }
            map.put("message","See your Quotations in My Quotes");
            return new ResponseEntity<>(map,HttpStatusCode.valueOf(200));
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }

    @GetMapping("/quote/getYourQuotations")
    public ResponseEntity<?> getYourQuotations(HttpServletRequest request,@RequestParam("page") int page){
        Map<String,Object> map= new HashMap<>();
        Long userId=jwtUtility.getUserId(request.getHeader("Authorization").substring(7));
        try{
            Page<RequestQuotation> list= quotationService.getYourQuotations(userId,page);
            if(!list.isEmpty()){
                return new ResponseEntity<>(list,HttpStatusCode.valueOf(200));
            }
            map.put("message","no quotations quoted by you");
            return new ResponseEntity<>(map,HttpStatusCode.valueOf(200));
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }


    @GetMapping("/quote/getQuoteById")
    public ResponseEntity<?> getQuoteByQuoteId(@RequestParam("qid") Long qid) {
        Map<String, Object> map = new HashMap<>();
        try {
            RequestQuotation requestQuotation = quotationService.getQuote(qid);
            if(requestQuotation!=null){
                return new ResponseEntity<>(requestQuotation,HttpStatusCode.valueOf(200));
            }
        }
        catch (Exception e) {
            map.put("message",e.getMessage());
        }
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }

    @PostMapping("/quote/acceptQuotation")
    public ResponseEntity<?> acceptQuotation(@RequestBody AcceptedQuotations acceptedQuotations){
        Map<String,Object> map = new HashMap<>();
        try{
            AcceptedQuotations acceptedQuotations1=quotationService.acceptQuotation(acceptedQuotations);
            return new ResponseEntity<>(acceptedQuotations1, HttpStatusCode.valueOf(200));
        }
        catch(Exception e){
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
        }
    }
    @GetMapping("/quote/getAcceptedQuotes")
    public ResponseEntity<?> getAcceptedQuotations(HttpServletRequest request,@RequestParam("page") int page){
        Map<String,Object> map = new HashMap<>();
        Long userId=jwtUtility.getUserId(request.getHeader("Authorization").substring(7));
        try{
            Page<AcceptedQuotations> acceptedQuotations=quotationService.getYourAcceptedQuotations(userId,page);
            if(!acceptedQuotations.isEmpty()){
                return new ResponseEntity<>(acceptedQuotations,HttpStatusCode.valueOf(200));
            }
            map.put("message","no accepted quotations yet");
            return  new ResponseEntity<>(map,HttpStatusCode.valueOf(200));
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }
    @PostMapping("/quote/createNegotiation")
    public ResponseEntity<?> createNegotiation(@RequestBody NegotiationRequest negotiationRequest){
        Map<String,Object> map = new HashMap<>();
        try{
            NegotiationRequest negotiationRequest1=quotationService.createNegotiation(negotiationRequest.getRequestQuotation().getId(),negotiationRequest.getUser().getId(),negotiationRequest.getCropPrice());
            if(negotiationRequest1!=null){
                return new ResponseEntity<>(negotiationRequest1,HttpStatusCode.valueOf(200));
            }
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }

    @GetMapping("/quote/getNegotiationsForQuotation")
    public ResponseEntity<?> getNegotiationsForQuotation(@RequestParam("qid") Long qid,@RequestParam("page") int page){
        Map<String,Object> map = new HashMap<>();
        try{
            Page<NegotiationRequest> list=quotationService.getNegotiations(qid,page);
            if(!list.isEmpty()){
                return new ResponseEntity<>(list,HttpStatusCode.valueOf(200));
            }
            map.put("message","no negotiations available");
            return new ResponseEntity<>(map,HttpStatusCode.valueOf(200));
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }

    @GetMapping("/quote/negotiationStatus")
    public ResponseEntity<?> negotiationStatus(HttpServletRequest request,@RequestParam("qid")Long qid){
        Long userId=jwtUtility.getUserId(request.getHeader("Authorization").substring(7));
        Map<String,Object> map = new HashMap<>();
        try{
            NegotiationRequest negotiationRequest =quotationService.getNegotiationRequest(userId,qid);
            if(negotiationRequest!=null){
                return new ResponseEntity<>(negotiationRequest,HttpStatusCode.valueOf(200));
            }
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }

    @PostMapping("/quote/createNegotiationResponse")
    public ResponseEntity<?> addNegotiationResponse(@RequestBody NegotiationResponse negotiationResponse){
        Map<String,Object> map = new HashMap<>();
        try{
            NegotiationResponse negotiationResponse1=quotationService.createNegotiationResponse(negotiationResponse);
            if(negotiationResponse1!=null){
                return new ResponseEntity<>(negotiationResponse1,HttpStatusCode.valueOf(200));
            }
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }

    @GetMapping("/quote/getNegotiationResponse")
    public ResponseEntity<?> getNegotiationResponse(@RequestParam("negotiationRequestId") Long negotiationRequestId){
        Map<String,Object> map= new HashMap<>();
        try{
            NegotiationResponse negotiationResponse=quotationService.getReponse(negotiationRequestId);
            if(negotiationResponse!=null){
                return new ResponseEntity<>(negotiationResponse,HttpStatusCode.valueOf(200));
            }
            map.put("message","no Negotiation Response for this Negotiation");
            return new ResponseEntity<>(map,HttpStatusCode.valueOf(200));
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }




}
