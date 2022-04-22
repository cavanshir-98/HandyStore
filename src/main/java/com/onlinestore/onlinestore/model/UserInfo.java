package com.onlinestore.onlinestore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//
//    private String name;
//    private String surname;
//    private String city;
//    private String phone;
//    @Transient
//    private MultipartFile image;
//    private LocalDateTime reg_date;
//
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private Set<Post> posts;

//    @OneToMany(mappedBy = "from", fetch = FetchType.LAZY)
//    private Set<Message> from_messages;
//
//    @OneToMany(mappedBy = "to", fetch = FetchType.LAZY)
//    private Set<Message> to_messages;

//    @OneToMany(mappedBy = "who")
//    private Set<Blocked> who_block;
//
//    @OneToMany(mappedBy = "whom")
//    private Set<Blocked> whom_block;
//
//    @OneToMany(mappedBy = "user")
//    private Set<ResetToken> resetTokens;




}
