package com.example.demo.Beans;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String itemName;


    @ManyToOne
    @JoinColumn(name="categories_id")
    private Categories categories;

    @ManyToOne
    @JoinColumn(name="unit_id")
    private Units units;

    @OneToMany(mappedBy = "subCategory")
    private List<CropDetails> cropDetails;

}
