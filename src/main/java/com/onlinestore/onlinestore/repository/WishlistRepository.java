package com.onlinestore.onlinestore.repository;

import com.onlinestore.onlinestore.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<WishList,Long> {
}
