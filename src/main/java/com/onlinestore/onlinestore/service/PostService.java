package com.onlinestore.onlinestore.service;

import com.onlinestore.onlinestore.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto addPost(PostDto postDto);

    PostDto updateById(Long id, PostDto postDto);

    void deleteById(Long id);

    List<PostDto> findAll();

    void addWishlist(Long id);

}
