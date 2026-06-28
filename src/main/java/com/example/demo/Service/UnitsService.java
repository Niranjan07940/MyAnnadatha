package com.example.demo.Service;

import com.example.demo.Beans.Units;
import com.example.demo.Repository.UnitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UnitsService {
    @Autowired
    private UnitsRepository unitsRepository;

    public List<Units> getUnits() {
        return unitsRepository.findAll();
    }
}
