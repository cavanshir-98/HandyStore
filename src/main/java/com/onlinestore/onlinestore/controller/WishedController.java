package com.onlinestore.onlinestore.controller;

import com.onlinestore.onlinestore.security.UserrDetails;
import com.onlinestore.onlinestore.service.CityService;
import com.onlinestore.onlinestore.service.PostService;
import com.onlinestore.onlinestore.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishedController {

    private final PostService postService;
    private final CityService cityService;
    private final UserServiceImpl userServiceImpl;

    @GetMapping
    public String getAllAddWish(Authentication authentication, Model model) {

        model.addAttribute("posts", postService.getAllWishList(getLoggedUser(authentication).getId()));
        model.addAttribute("user", userServiceImpl.findAll());
        model.addAttribute("city", cityService.getAll());
        return "wished";
    }

    @GetMapping("/add/{id}")
    public String AddWish(Model model, @PathVariable Long id) {

        model.addAttribute("wished", postService.addWishlist(id));
        return "redirect:/wishlist";
    }

    @GetMapping("/delete/{id}")
    public String deleteWish(Model model, @PathVariable Long id) {

        model.addAttribute("wished", postService.deleteByIdForWishlist(id));
        return "redirect:/wishlist";

    }

    UserrDetails getLoggedUser(Authentication authentication) {
        return (UserrDetails) authentication.getPrincipal();
    }

}
