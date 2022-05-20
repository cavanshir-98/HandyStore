package com.onlinestore.onlinestore.repository;

import com.onlinestore.onlinestore.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findById(Long id);

    @Query("SELECT post from Post as post where post.user.id = :id")
    List<Post> findByUserId(Long id);


    Page<Post> findAllByNameContainingIgnoreCaseAndCategory_IdAndStatus(String name, Long categoryId, boolean status, Pageable pageable);
}
