package com.onlinestore.onlinestore.service.impl;

import com.onlinestore.onlinestore.model.Feedback;
import com.onlinestore.onlinestore.repository.FeedbackRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepo feedbackRepo;

    public Feedback saveFeedback(Feedback text) {
        Feedback feedback = new Feedback();
        feedback.setId(text.getId());
        feedback.setText(text.getText());
        return feedbackRepo.save(feedback);
    }

}
