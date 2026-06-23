package com.example.demo.Beans;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Units {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String unit;

    @OneToMany(mappedBy = "units")
    private List<SubCategory> subCategories;

}
