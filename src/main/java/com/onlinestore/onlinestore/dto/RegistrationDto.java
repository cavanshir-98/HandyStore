package com.onlinestore.onlinestore.dto;

import lombok.Data;

@Data
public class RegistrationDto {
    private String email;
    private String pass;
    private String conPass;
}
