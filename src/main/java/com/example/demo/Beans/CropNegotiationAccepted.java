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
public class CropNegotiationAccepted {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double acceptedPrice;

    @ManyToOne
    @JoinColumn(name="buyerId")
    private User buyer;

    @OneToOne
    @JoinColumn(name="cropDetailsId")
    private CropDetails cropDetails;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;


}
