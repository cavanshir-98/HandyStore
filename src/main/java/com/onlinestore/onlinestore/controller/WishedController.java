package com.onlinestore.onlinestore.controller;

import com.onlinestore.onlinestore.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wish")
@RequiredArgsConstructor
public class WishedController {

    private final PostService postService;



    @PutMapping("/{id}")
    public void AddWish (@PathVariable Long id){
         postService.addWishlist(id);
    }

}
