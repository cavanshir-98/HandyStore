package com.onlinestore.onlinestore.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    //    User user;// Securityden sonra olacaq
    @OneToOne
    @JoinColumn(name = "city_Id")
    City city;
    String date;
}
