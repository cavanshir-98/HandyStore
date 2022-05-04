package com.onlinestore.onlinestore.controller;


import com.onlinestore.onlinestore.model.Userr;
import com.onlinestore.onlinestore.security.UserrDetails;
import com.onlinestore.onlinestore.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserViewController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping("/{id}")
    public String userView(@PathVariable String id, Model model, Authentication au) {
        Userr user = userServiceImpl.findById(String.valueOf(getLoggedUser(au).getId()));

        model.addAttribute("user", user);
        model.addAttribute("user", userServiceImpl.viewUser(id, getLoggedUser(au).getId()));
        return "user";
    }

    UserrDetails getLoggedUser(Authentication authentication) {
        return (UserrDetails) authentication.getPrincipal();
    }

}