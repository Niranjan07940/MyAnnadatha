package com.example.demo.Beans;

import com.example.demo.Enum.CropDetailsStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
public class CropDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Double cropQuantity;
    private Double cropPrice;
    private List<String> imageUrls;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
    @ManyToOne
    @JoinColumn(name="subCategoryId")
    private SubCategory subCategory;

    @ManyToOne
    @JoinColumn(name="cropLocation_id")
    private CropLocation cropLocation;

    @Enumerated(EnumType.STRING)
    private CropDetailsStatus cropDetailsStatus;

    @PrePersist
    private void onCreate(){
        if(cropDetailsStatus==null){
            cropDetailsStatus= CropDetailsStatus.WAITING;
        }
    }

}

