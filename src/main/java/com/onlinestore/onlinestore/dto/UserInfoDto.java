package com.onlinestore.onlinestore.dto;

import com.onlinestore.onlinestore.model.City;
import lombok.Data;

import java.util.UUID;

@Data
public class UserInfoDto {

    Long id;

    String name;

    String surname;

    City city;

    String number;

    UUID imageId;

}
