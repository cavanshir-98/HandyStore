package com.onlinestore.onlinestore.dto;

import com.onlinestore.onlinestore.model.Category;
import com.onlinestore.onlinestore.model.City;
import lombok.Data;

import java.util.UUID;

@Data

public class PostDto {


    private Long id;
    private String name;
    private City city;
    private String date;
//    private UUID imageId;
    private Category category;


}
