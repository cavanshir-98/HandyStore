package com.onlinestore.onlinestore.repository;


import com.onlinestore.onlinestore.model.Userr;
import com.onlinestore.onlinestore.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Userr, Long> {

    Optional<Userr> findUserrByEmail(String email);

    Optional<Userr> findByEmail(String name);


}

