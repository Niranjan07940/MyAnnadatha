package com.example.demo.Controller;

import com.example.demo.Beans.Units;
import com.example.demo.Service.UnitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class UnitsController {

    @Autowired
    private UnitsService unitsService;

    @GetMapping("/units/getUnits")
    public ResponseEntity<?> getUnits(){
        return new ResponseEntity<>(unitsService.getUnits(), HttpStatusCode.valueOf(200));
    }
}
