package com.example.demo.Beans;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class CropLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double longitude;
    private Double latitude;


    @CreationTimestamp
    @Column(updatable = false)

    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;


}
