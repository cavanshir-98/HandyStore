package com.onlinestore.onlinestore.dto;

import com.onlinestore.onlinestore.model.City;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

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
