package com.onlinestore.onlinestore.service.impl;

import com.onlinestore.onlinestore.dto.DashBoardDto;
import com.onlinestore.onlinestore.model.DashBoard;
import com.onlinestore.onlinestore.model.Post;
import com.onlinestore.onlinestore.repository.DashBoardRepository;
import com.onlinestore.onlinestore.repository.PostRepository;
import com.onlinestore.onlinestore.service.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashBoardServiceImpl implements DashBoardService {

    private final DashBoardRepository dashBoardRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;


    @Override
    public DashBoardDto getName() {
        List<Post> posts = postRepository.findAll();
        List<DashBoard> dashBoards = dashBoardRepository.findAll();
        Post post = modelMapper.map(posts, Post.class);
        DashBoard dashBoard = modelMapper.map(dashBoards, DashBoard.class);
        dashBoard.setName(post.getName());
        return  modelMapper.map(dashBoard,DashBoardDto.class);

    }

    @Override
    public DashBoardDto getCity() {
        List<Post> posts = postRepository.findAll();
        List<DashBoard> dashBoards = dashBoardRepository.findAll();
        Post post = modelMapper.map(posts, Post.class);
        DashBoard dashBoard = modelMapper.map(dashBoards, DashBoard.class);
        dashBoard.setCity(post.getCity());
        return  modelMapper.map(dashBoard,DashBoardDto.class);    }

    @Override
    public DashBoardDto getDate() {
        List<Post> posts = postRepository.findAll();
        List<DashBoard> dashBoards = dashBoardRepository.findAll();
        Post post = modelMapper.map(posts, Post.class);
        DashBoard dashBoard = modelMapper.map(dashBoards, DashBoard.class);
        dashBoard.setDate(post.getDate());
        return  modelMapper.map(dashBoard,DashBoardDto.class);
    }
}

