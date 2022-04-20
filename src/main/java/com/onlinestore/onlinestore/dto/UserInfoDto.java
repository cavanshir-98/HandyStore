package com.onlinestore.onlinestore.dto;

import com.onlinestore.onlinestore.model.City;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfoDto {

    Long id;

    String name;

    String surname;

    City city;

    String number;

    UUID imageId;

}
