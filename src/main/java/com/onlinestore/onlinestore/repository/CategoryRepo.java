package com.onlinestore.onlinestore.repository;

import com.onlinestore.onlinestore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
