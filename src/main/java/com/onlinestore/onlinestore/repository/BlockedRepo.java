package com.onlinestore.onlinestore.repository;


import com.onlinestore.onlinestore.model.Blocked;
import com.onlinestore.onlinestore.model.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlockedRepo extends JpaRepository<Blocked, Long> {
    List<Blocked> findByWho(Userr loggedUser);

    Optional<Blocked> findByWhoAndWhom(Userr who, Userr whom);
}
