package com.onlinestore.onlinestore.controller;


import com.onlinestore.onlinestore.model.Post;
import com.onlinestore.onlinestore.service.CategoryService;
import com.onlinestore.onlinestore.service.CityService;
import com.onlinestore.onlinestore.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {


    private final PostService postService;
    private final CategoryService categoryService;
    private final CityService cityService;


    @RequestMapping
    public RedirectView firstMain() {
    return new RedirectView("/dashboard/1");
    }


    @GetMapping("/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int pageNumber) {

        Page<Post> page = postService.findPage(pageNumber);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Post> posts = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("posts", posts);
//        model.addAttribute("loggedUser","test@");
//        model.addAttribute("category",categoryService.getAll());

        return "dashboard";
    }


}

