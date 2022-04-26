package com.onlinestore.onlinestore.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @OneToOne
    City city;

    @DateTimeFormat(pattern = "mm/dd/yyyy")
    String date;

    String image;

    @OneToOne
    private Userr user;


    @OneToOne
    @JoinColumn(name = "category_Id")
    Category category;


}
