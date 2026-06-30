package com.example.demo.DTO;

import com.example.demo.Beans.CropDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NearbyCropResponse {

    private CropDetails cropDetails;
    private Double distance;

}
