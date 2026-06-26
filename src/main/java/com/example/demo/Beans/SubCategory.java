package com.example.demo.Beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @Column(unique = true,nullable = false)
    private String itemName;


    @ManyToOne
    @JoinColumn(name="categories_id")
    private Categories categories;

    @ManyToOne
    @JoinColumn(name="unit_id")
    private Units units;


}
