package com.onlinestore.onlinestore.service.impl;

import com.onlinestore.onlinestore.dto.PostDto;
import com.onlinestore.onlinestore.model.Post;
import com.onlinestore.onlinestore.model.WishList;
import com.onlinestore.onlinestore.repository.PostRepository;
import com.onlinestore.onlinestore.repository.UserRepo;
import com.onlinestore.onlinestore.repository.WishlistRepository;
import com.onlinestore.onlinestore.security.UserrDetails;
import com.onlinestore.onlinestore.service.CloudinaryAdapter;
import com.onlinestore.onlinestore.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {


    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final WishlistRepository wishlistRepository;
    private final CloudinaryAdapter cloudinaryAdapter;
    private final UserRepo userRepo;

    @Override
    public Post addPost(Authentication authentication, PostDto postDto) throws Exception {
        Post post = new Post();
        post.setName(postDto.getName());
        post.setCity(postDto.getCity());
        post.setCategory(postDto.getCategory());
        post.setDate(LocalDate.parse(postDto.getDate()));
        post.setUser(userRepo.getById(getLoggedUser(authentication).getId()));


        MultipartFile multipartFile = postDto.getImage();
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();

        Map<String, String> uploadImage = cloudinaryAdapter.uploadImageCloudinary(file);
        file.delete();

        String url = uploadImage.get("url");
        post.setImage(url);

        return this.postRepository.save(post);
    }

    @Override
    public PostDto updateById(Long id, PostDto postDto) throws Exception {

        Post updatePost = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        updatePost.setName(postDto.getName());
        updatePost.setCity(postDto.getCity());
        updatePost.setCategory(postDto.getCategory());
        updatePost.setDate(LocalDate.parse(postDto.getDate()));

        MultipartFile multipartFile = postDto.getImage();
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();

        Map<String, String> uploadImage = cloudinaryAdapter.uploadImageCloudinary(file);
        file.delete();

        String url = uploadImage.get("url");
        updatePost.setImage(url);
        postRepository.save(updatePost);
        return modelMapper.map(updatePost, PostDto.class);
    }

    @Override
    public List<Post> findByIdForMyPost(Long id) {

        List<Post> byUserId = postRepository.findByUserId(id);
        return byUserId;
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
    public WishList addWishlist(Long id) {

        WishList wishList = new WishList();
        Post post = postRepository.getById(id);

        wishList.setUser(post.getUser());
        wishList.setName(post.getName());
        wishList.setId(post.getId());
        wishList.setImage(post.getImage());
        wishList.setDate(String.valueOf(post.getDate()));
        wishList.setCity(post.getCity());
        wishList.setCategory(post.getCategory());

        return wishlistRepository.save(wishList);


    }

    @Override
    public Object deleteByIdForWishlist(Long id) {
        wishlistRepository.deleteById(id);
        return ResponseEntity.ok();
    }

    @Override
    public List<WishList> getAllWishList(Long id) {
        return wishlistRepository.findByUserIdForWishlist(id);
    }

    @Override
    public Page<Post> findPage(int currentPage) {
        Pageable pageable = PageRequest.of(currentPage - 1, 10);
        return postRepository.findAll(pageable);
    }

    @Override
    public PostDto getById(Long id) {
        Post getById = postRepository.getById(id);
        return modelMapper.map(getById, PostDto.class);
    }

    UserrDetails getLoggedUser(Authentication authentication) {
        return (UserrDetails) authentication.getPrincipal();
    }
}



