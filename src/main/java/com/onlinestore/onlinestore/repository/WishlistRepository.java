package com.onlinestore.onlinestore.repository;

import com.onlinestore.onlinestore.model.Post;
import com.onlinestore.onlinestore.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishlistRepository extends JpaRepository<WishList,Long> {

    @Query("SELECT wishlist from WishList as wishlist where wishlist.user.id = :id")
   List< WishList >findByUserIdForWishlist(Long id);
}
