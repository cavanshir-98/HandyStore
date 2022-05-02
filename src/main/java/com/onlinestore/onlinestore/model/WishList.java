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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "wishlist_user",
            joinColumns = {@JoinColumn(name="wishlist_id")
            },
            inverseJoinColumns = {@JoinColumn(name = "user_id",
                    referencedColumnName = "u_id")})
    Userr user;


    @OneToOne
    @JoinColumn(name = "category_Id")
    Category category;


}
