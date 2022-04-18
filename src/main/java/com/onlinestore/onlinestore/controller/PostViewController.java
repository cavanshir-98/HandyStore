package com.onlinestore.onlinestore.controller;

import com.onlinestore.onlinestore.service.CategoryService;
import com.onlinestore.onlinestore.service.CityService;
import com.onlinestore.onlinestore.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/myposts")
@RequiredArgsConstructor
public class PostViewController {

    private final PostService postService;
    private final CategoryService categoryService;
    private final CityService cityService;

//    @RequestMapping
//    public RedirectView firstMain() {
//        return new RedirectView("/myposts/1");
//    }

    @GetMapping
    public String userPost (Model model){
        model.addAttribute("posts", postService.findAll());
        model.addAttribute("category",categoryService.getAll());
        return "manage-post";
    }
}
