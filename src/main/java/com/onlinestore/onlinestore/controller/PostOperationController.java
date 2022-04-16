package com.onlinestore.onlinestore.controller;

import com.onlinestore.onlinestore.dto.PostDto;
import com.onlinestore.onlinestore.service.CategoryService;
import com.onlinestore.onlinestore.service.CityService;
import com.onlinestore.onlinestore.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostOperationController {

    private final PostService postService;
    private final CategoryService categoryService;
    private final CityService cityService;

    @GetMapping
    public String getAllPost(Model model) {

        model.addAttribute("post", postService.findAll());
        model.addAttribute("category", categoryService.getAll());
        model.addAttribute("city", cityService.getAll());

        return "a_u-post";
    }

    @PostMapping
    public String createPost(@ModelAttribute PostDto postDto) {

        postService.addPost(postDto);

        return "redirect:/post";
    }


    @PutMapping("/{id}")
    public PostDto updateById(@Valid @PathVariable Long id,
                              @RequestBody PostDto postDto) {
        return postService.updateById(id, postDto);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        postService.deleteById(id);
    }


}

