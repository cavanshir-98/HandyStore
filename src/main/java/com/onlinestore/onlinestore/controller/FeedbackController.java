package com.onlinestore.onlinestore.controller;


import com.onlinestore.onlinestore.model.Feedback;
import com.onlinestore.onlinestore.security.UserrDetails;
import com.onlinestore.onlinestore.service.impl.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@RequiredArgsConstructor
@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping
    public String handle_get() {
        return "feedback";
    }

    @PostMapping
    public RedirectView handle_post(@ModelAttribute Feedback feedback, Model model) {
        model.addAttribute("feedback", feedbackService.saveFeedback(feedback));
        return new RedirectView("/dashboard/1");
    }

    UserrDetails getLoggedUser(Authentication authentication) {
        return (UserrDetails) authentication.getPrincipal();
    }
}
