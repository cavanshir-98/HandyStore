package com.onlinestore.onlinestore.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfoDto {


    String email;

    String name;

    String surname;

    String city;

    String number;

    MultipartFile imageId;

}
