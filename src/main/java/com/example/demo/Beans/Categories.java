package com.example.demo.Beans;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class Categories {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;
    private String categoryName;

    @OneToMany(mappedBy = "categories")
    private List<SubCategory> subCategory;

}
