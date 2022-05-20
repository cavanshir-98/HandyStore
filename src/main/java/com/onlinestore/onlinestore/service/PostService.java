package com.onlinestore.onlinestore.service;

import com.onlinestore.onlinestore.dto.PostDto;
import com.onlinestore.onlinestore.model.Post;
import com.onlinestore.onlinestore.model.WishList;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface PostService {

    Post addPost(Authentication authentication, PostDto postDto) throws Exception;

    PostDto updateById(Long id, PostDto postDto) throws Exception;

    List<Post> findByIdForMyPost(Long id);

    Object deleteById(Long id);

    List<PostDto> findAll();

    WishList addWishlist(Authentication authentication,Long id);

    Object deleteByIdForWishlist(Long id);

    List<WishList> getAllWishList();

    Page<Post> findPage(int currentPage);

    PostDto getById(Long id);


}
