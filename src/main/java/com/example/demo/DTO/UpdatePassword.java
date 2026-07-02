package com.example.demo.DTO;

import lombok.Data;

@Data
public class UpdatePassword {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
