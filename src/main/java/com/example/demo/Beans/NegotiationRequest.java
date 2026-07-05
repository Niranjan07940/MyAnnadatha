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
public class NegotiationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Double cropPrice;

    @ManyToOne
    @JoinColumn(name="requestQuotation_id")
    private RequestQuotation requestQuotation;

    @ManyToOne
    @JoinColumn(name="farmer_id")
    private User user;


    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;


}
