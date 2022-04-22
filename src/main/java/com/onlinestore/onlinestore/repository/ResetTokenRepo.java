package com.onlinestore.onlinestore.repository;


import com.onlinestore.onlinestore.model.ResetToken;
import com.onlinestore.onlinestore.model.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResetTokenRepo extends JpaRepository<ResetToken, Long> {
    List<ResetToken> findAllByUser(Userr user);

    void deleteByUser(Userr user);
}
