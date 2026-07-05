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
public class NegotiationResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double cropPrice;

    @OneToOne
    @JoinColumn(name="negotiationRequest_id")
    private NegotiationRequest negotiationRequest;

    @ManyToOne
    @JoinColumn(name="buyerId")
    private User buyer;

    @ManyToOne
    @JoinColumn(name="userId")
    private User farmer;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;


}
