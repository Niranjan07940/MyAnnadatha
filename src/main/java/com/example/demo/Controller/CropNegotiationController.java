package com.example.demo.Controller;

import com.example.demo.Beans.CropNegotiationAccepted;
import com.example.demo.Beans.CropNegotiationRequest;
import com.example.demo.Beans.CropNegotiationResponse;
import com.example.demo.DTO.CropOrdered;
import com.example.demo.Service.CropNegotiationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin
@RestController
public class CropNegotiationController {

    @Autowired
    private CropNegotiationService cropNegotiationService;

    @PostMapping("/cropNegotiation/createCropNegotiationRequest")
    public ResponseEntity<?> createNegotiationRequest(@RequestBody CropNegotiationRequest cropNegotiationRequest) {
        Map<String,Object> map = new HashMap<>();
        try{
            CropNegotiationRequest cropNegotiationRequest1=cropNegotiationService.createRequest(cropNegotiationRequest);
            if(cropNegotiationRequest1!=null){
                return ResponseEntity.ok().body(cropNegotiationRequest1);
            }
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/cropNegotiation/createCropNegotiationResponse")
    public ResponseEntity<?> createNegotiationResponse(@RequestBody CropNegotiationResponse cropNegotiationResponse) {
        Map<String,Object> map = new HashMap<>();
        try{
            CropNegotiationResponse cropNegotiationResponse1=cropNegotiationService.createNegotiationResponse(cropNegotiationResponse);
            if(cropNegotiationResponse1!=null){
                return ResponseEntity.ok().body(cropNegotiationResponse1);
            }
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new  ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/cropNegotiation/getNegotiationRequests")
    public ResponseEntity<?> getCropNegotiationRequestByYou(@RequestParam("buyerId")Long buyerId){
        Map<String,Object> map = new HashMap<>();
        try{
            CropNegotiationRequest cropNegotiationRequest=cropNegotiationService.getYourRequest(buyerId);
            if(cropNegotiationRequest!=null){
                return new ResponseEntity<>(cropNegotiationRequest, HttpStatus.OK);
            }
            map.put("message","No Negotiation Requests by you yet!");
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(400));
    }

    @GetMapping("/cropNegotiation/getCropNegotiationResponse")
    public ResponseEntity<?> getCropNegotiationResponse(@RequestParam("cropNegotiationRequestId") Long cropNegotiationRequestId){
        Map<String,Object> map = new HashMap<>();
        try{
            CropNegotiationResponse cropNegotiationResponse=cropNegotiationService.getNegotiationResponse(cropNegotiationRequestId);
            if(cropNegotiationResponse!=null){
                return new ResponseEntity<>(cropNegotiationResponse, HttpStatus.OK);
            }
            map.put("message","No Negotiation Response!");
            return new  ResponseEntity<>(map,HttpStatus.OK);
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }

    @GetMapping("/cropNegotiation/getAllNegotiationsForCrop")
    public ResponseEntity<?> getCropNegotiationsForCrop(@RequestParam("cropDetailsId")Long cropDetailsId,@RequestParam("page") int page){
        Map<String,Object> map = new HashMap<>();
        try{
            Page<CropNegotiationRequest> cropNegotiationRequestPage=cropNegotiationService.getNegotiations(cropDetailsId,page);
            if(!cropNegotiationRequestPage.isEmpty()){
                return new ResponseEntity<>(cropNegotiationRequestPage, HttpStatus.OK);
            }
            map.put("message","No Negotiation Requests!");
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new  ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }

    @PostMapping("/negotiationRequest/acceptNegotiation")
    public ResponseEntity<?> acceptCropNegotiation(@RequestBody CropOrdered cropOrdered){
        Map<String,Object> map = new HashMap<>();
        try{
            CropNegotiationAccepted cropNegotiationAccepted1=cropNegotiationService.acceptNegotiation(cropOrdered);
            if(cropNegotiationAccepted1!=null){
                return new ResponseEntity<>(cropNegotiationAccepted1, HttpStatus.OK);
            }
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new  ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }

    @GetMapping("/cropNegotiation/getAcceptedCrops")
    public ResponseEntity<?> getAcceptedCropDetails(@RequestParam("cropDetailsId") Long cropDetailsId){
        Map<String,Object> map = new HashMap<>();
        try{
            CropNegotiationAccepted cropNegotiationAccepted=cropNegotiationService.getAllAcceptedCropDetails(cropDetailsId);
            if(cropNegotiationAccepted!=null){
                return new ResponseEntity<>(cropNegotiationAccepted, HttpStatus.OK);
            }
            map.put("message","No people accepted your crops yet!");
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
        catch(Exception e){
            map.put("message",e.getMessage());
        }
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(400));
    }
}
