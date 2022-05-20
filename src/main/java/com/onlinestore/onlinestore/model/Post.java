package com.onlinestore.onlinestore.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    long id;
    String name;

    @OneToOne
    City city;

    @OneToOne
    WishList wishList;
    String image;
    boolean status;
    LocalDate expiry_date;
    LocalDate date;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "r_post_user",
            joinColumns = {@JoinColumn(name = "post_id",
                    referencedColumnName = "p_id"),
            },
            inverseJoinColumns = {@JoinColumn(name = "user_id",
                    referencedColumnName = "u_id")})
    Userr user;


    @OneToOne
    private Category category;

    public Post(Userr user, String name, Category category, City city, String image, LocalDate expiry_date) {
        this.user = user;
        this.name = name;
        this.category = category;
        this.city = city;
        this.image = image;
        this.expiry_date = expiry_date;
        this.status = true;
        this.date = LocalDate.now();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Post{id=%d}", id);
    }


}
