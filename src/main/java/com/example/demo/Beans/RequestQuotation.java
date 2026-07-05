package com.example.demo.Beans;

import com.example.demo.Enum.NegotiationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Timestamp;


@Getter
@Setter
@Entity
public class RequestQuotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String cropName;

    private Double cropQuantity;
    private Double cropPrice;

    private String deliveryLocation;

    private Date requiredDate;


    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private NegotiationStatus negotiationStatus;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    @PrePersist
    private void onCreate(){
        if(negotiationStatus==null){
            negotiationStatus=NegotiationStatus.WAITING;
        }
    }

}
