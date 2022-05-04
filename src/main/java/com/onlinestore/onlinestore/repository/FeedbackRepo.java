package com.onlinestore.onlinestore.repository;

import com.onlinestore.onlinestore.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepo extends JpaRepository<Feedback, Long> {
}
