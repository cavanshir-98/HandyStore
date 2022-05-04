package com.onlinestore.onlinestore.controller;

import com.onlinestore.onlinestore.dto.RegistrationDto;
import com.onlinestore.onlinestore.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@AllArgsConstructor
@Controller
@RequestMapping("/signup")
public class RegistrationController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping
    public String register() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/dashboard";
        }
        return "signup";
    }

    @PostMapping
    public RedirectView saveUser(RegistrationDto form) {
        userServiceImpl.register(form.getEmail(), form.getPass(), form.getConPass());
        return new RedirectView("/signin");
    }
}
