package com.onlinestore.onlinestore.controller;

import com.onlinestore.onlinestore.repository.WishlistRepository;
import com.onlinestore.onlinestore.service.CategoryService;
import com.onlinestore.onlinestore.service.CityService;
import com.onlinestore.onlinestore.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishedController {

    private final PostService postService;
    private final CategoryService categoryService;
    private final CityService cityService;

    @GetMapping
    public String getAllAddWish(Model model){

        model.addAttribute("posts", postService.getAllWishList());
        model.addAttribute("category", categoryService.getAll());
        model.addAttribute("city", cityService.getAll());
        return "wished";
    }


    @GetMapping("/add/{id}")
    public String AddWish (Model model, @PathVariable Long id){

        model.addAttribute( "wished",postService.addWishlist(id));
         return "redirect:/wishlist";
    }

    @GetMapping("/delete/{id}")
    public String deleteWish(Model model, @PathVariable Long id) {

        model.addAttribute("wished", postService.deleteByIdForWishlist(id));
        return "redirect:/wishlist";

    }

}
