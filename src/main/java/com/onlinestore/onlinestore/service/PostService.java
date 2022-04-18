package com.onlinestore.onlinestore.service;

import com.onlinestore.onlinestore.dto.PostDto;
import com.onlinestore.onlinestore.model.Post;
import com.onlinestore.onlinestore.model.WishList;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {
    PostDto addPost(PostDto postDto);

    PostDto updateById(Long id, PostDto postDto);

    Object deleteById(Long id);

    List<PostDto> findAll();

    Object addWishlist(Long id);

    Object deleteByIdForWishlist(Long id);

    List<WishList> getAllWishList();

    Page<Post> findPage(int currentPage);

}
