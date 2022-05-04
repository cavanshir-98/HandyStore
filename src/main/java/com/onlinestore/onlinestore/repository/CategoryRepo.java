package com.onlinestore.onlinestore.repository;

import com.onlinestore.onlinestore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
//    //
//    @Query("select a from Post a join fetch a.branch where a.accountNumber = ?1")
//    List<Category> findByPostId(Long id);
}
