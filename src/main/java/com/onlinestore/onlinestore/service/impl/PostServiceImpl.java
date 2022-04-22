package com.onlinestore.onlinestore.service.impl;

import com.onlinestore.onlinestore.dto.PostDto;
import com.onlinestore.onlinestore.model.Post;
import com.onlinestore.onlinestore.model.WishList;
import com.onlinestore.onlinestore.repository.PostRepository;
import com.onlinestore.onlinestore.repository.WishlistRepository;
import com.onlinestore.onlinestore.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final WishlistRepository wishlistRepository;

    @Override
    public PostDto addPost(PostDto postDto) {
        Post postSave = postRepository.save(modelMapper.map(postDto, Post.class));
        return modelMapper.map(postSave, PostDto.class);
    }

    @Override
    public PostDto updateById(Long id, PostDto postDto) {

        Post updatePost = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        PostDto dto = modelMapper.map(updatePost, PostDto.class);
        return modelMapper.map(dto, PostDto.class);
    }

    @Override
    public Object deleteById(Long id) {
        postRepository.deleteById(id);
        return ResponseEntity.ok();
    }


    @Override
    public List<PostDto> findAll() {
        List<PostDto> all = postRepository.findAll().stream().map(dto -> {
            PostDto postDto = modelMapper.map(dto, PostDto.class);
            return postDto;
        }).collect(Collectors.toList());
        return all;

    }

    @Override
    public Object addWishlist(Long id) {

        WishList wishList = new WishList();
        Post post = postRepository.getById(id);

        wishList.setName(post.getName());
        wishList.setId(post.getId());
//        wishList.setImageId(post.getImage());
//        wishList.setDate(post.getDate());
//        wishList.setCity(post.getCity());
        wishList.setCategory(post.getCategory());
        wishlistRepository.save(wishList);
        return wishList;
    }

    @Override
    public Object deleteByIdForWishlist(Long id) {
       wishlistRepository.deleteById(id);
       return ResponseEntity.ok();
    }

    @Override
    public List<WishList> getAllWishList() {
        return wishlistRepository.findAll();
    }


    @Override
    public Page<Post> findPage(int currentPage) {
        Pageable pageable = PageRequest.of(currentPage - 1, 10);
        return postRepository.findAll(pageable);
    }


}



