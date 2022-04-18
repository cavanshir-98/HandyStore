package com.onlinestore.onlinestore.controller;

import com.onlinestore.onlinestore.dto.PostDto;
import com.onlinestore.onlinestore.service.CategoryService;
import com.onlinestore.onlinestore.service.CityService;
import com.onlinestore.onlinestore.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypost")
public class PostOperationController {

    private final PostService postService;
    private final CategoryService categoryService;
    private final CityService cityService;

//    @RequestMapping
//    public RedirectView firstMain() {
//        return new RedirectView("/mypost");
//    }


    @GetMapping("/0")
    public String getAllPost(Model model) {

        model.addAttribute("post", postService.findAll());
        model.addAttribute("category", categoryService.getAll());
        model.addAttribute("city", cityService.getAll());

        return "a_u-post";
    }

    @PostMapping("/0")
    public String createPost(@ModelAttribute PostDto postDto) {

        postService.addPost(postDto);

        return "redirect:/myposts";
    }


    @PutMapping("/0/{id}")
    public String updateById( Model model,@PathVariable Long id,
                              @ModelAttribute PostDto postDto) {
       model.addAttribute("post",postService.updateById(id,postDto));
    return "redirect:/mypost";
    }

    @DeleteMapping("/{id}")
    public String deleteById( Model model,@PathVariable Long id,
                            @ModelAttribute PostDto postDto) {
        model.addAttribute("post",postService.deleteById(id));
        return "redirect:/mypost";
    }


}

