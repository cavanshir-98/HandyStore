package com.onlinestore.onlinestore.controller;

import com.onlinestore.onlinestore.dto.PostDto;
import com.onlinestore.onlinestore.service.CategoryService;
import com.onlinestore.onlinestore.service.CityService;
import com.onlinestore.onlinestore.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypost")
public class PostOperationController {

    private final PostService postService;
    private final CategoryService categoryService;
    private final CityService cityService;

    @GetMapping("/0")
    public String getAllPost(Model model) {

        model.addAttribute("post", postService.findAll());
        model.addAttribute("category", categoryService.getAll());
        model.addAttribute("city", cityService.getAll());
        model.addAttribute("image", postService.findAll());

        return "a_u-post";
    }

    @PostMapping("/0")
    public String createPost(Authentication authentication, @ModelAttribute PostDto postDto) throws Exception {

        postService.addPost(authentication, postDto);

        return "redirect:/myposts";
    }

}

