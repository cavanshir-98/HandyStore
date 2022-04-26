package com.onlinestore.onlinestore.dto;

//import com.onlinestore.onlinestore.model.Categoryy;
import com.onlinestore.onlinestore.model.Category;
import com.onlinestore.onlinestore.model.City;
import com.onlinestore.onlinestore.model.Userr;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class PostDto {


    private Long id;
    private String name;
    private City city;
    private String date;
    private MultipartFile image;
    private Category category;
    private Userr userr;


}
