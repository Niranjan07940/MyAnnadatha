package com.example.demo.Beans;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name="Users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private Long phoneNumber;
    private String profileUrl;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
