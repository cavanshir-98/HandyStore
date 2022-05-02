package com.onlinestore.onlinestore.controller;

import com.onlinestore.onlinestore.dto.PostDto;
import com.onlinestore.onlinestore.repository.PostRepository;
import com.onlinestore.onlinestore.security.UserrDetails;
import com.onlinestore.onlinestore.service.CategoryService;
import com.onlinestore.onlinestore.service.CityService;
import com.onlinestore.onlinestore.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/myposts")
@RequiredArgsConstructor
public class PostViewController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final CityService cityService;

    @GetMapping
    public String userPost(Model model, Authentication authentication) {
        model.addAttribute("posts", postService.findByIdForMyPost(getLoggedUser(authentication).getId()));
        return "manage-post";
    }

    @GetMapping("/{id}")
    public String getPostUpdateById(Model model, @PathVariable Long id) {
        model.addAttribute("post", postRepository.getById(id));
        model.addAttribute("category", categoryService.getAll());
        model.addAttribute("city", cityService.getAll());
        return "up-post";
    }

    @PostMapping("/{id}")
    public String updateById(Model model, @PathVariable Long id,
                             @ModelAttribute PostDto postDto) throws Exception {

        postService.updateById(id, postDto);
        return "redirect:/myposts";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(Model model, @PathVariable Long id) {
        model.addAttribute("post", postService.deleteById(id));
        return "redirect:/myposts";
    }

    UserrDetails getLoggedUser(Authentication authentication) {
        return (UserrDetails) authentication.getPrincipal();
    }

}
