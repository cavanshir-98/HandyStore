package com.onlinestore.onlinestore.repository;



import com.onlinestore.onlinestore.model.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Userr, Long> {

  Optional<Userr> findUserrByEmail(String email);
  Optional<Userr>findByEmail(String name);
}

