package com.example.demo.Beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name="Users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String username;
    @Email
    @Column(unique = true,nullable = false)
    private String email;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Long phoneNumber;
    private String profileUrl;


    private Double totalRating=0.0;

    private Integer count=0;

    private Integer favouriteCount=0;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
